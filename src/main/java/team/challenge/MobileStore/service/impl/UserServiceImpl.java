package team.challenge.MobileStore.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.challenge.MobileStore.dto.UserRequest;
import team.challenge.MobileStore.exception.ModelIsAlreadyExist;
import team.challenge.MobileStore.exception.ModelNotFoundException;
import team.challenge.MobileStore.model.RoleModel;
import team.challenge.MobileStore.model.UserModel;
import team.challenge.MobileStore.repositories.RoleRepository;
import team.challenge.MobileStore.repositories.UserRepository;
import team.challenge.MobileStore.service.UserService;

import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public List<?> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserModel getOneById(@NonNull String id) {
        return userRepository.findById(id).orElseThrow(()-> new ModelNotFoundException(String.format("User with id: %s not found", id)));
    }

    @Override
    public UserModel getOneByEmail(@NonNull String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new  ModelNotFoundException(String.format("User with email: %s not found", email)));
    }

    @Override
    public UserModel create(@NonNull UserRequest userRequest) {
        try{
            getOneByEmail(userRequest.email());
            throw new ModelIsAlreadyExist(String.format("User with email: %s is already exist!", userRequest.email()));
        } catch (ModelNotFoundException e){
            RoleModel userRole = roleRepository.findByRoleName("USER").orElseGet(() -> {
                RoleModel newRole = new RoleModel();
                newRole.setRoleName("USER");
                return roleRepository.save(newRole);
            });
            UserModel user = UserModel.builder()
                    .email(userRequest.email())
                    .firstname(userRequest.firstname())
                    .lastname(userRequest.lastname())
                    .password(passwordEncoder.encode(userRequest.password()))
                    .gender(userRequest.gender())
                    .picture(userRequest.picture())
                    .roleModels(Collections.singleton(userRole))
                    .build();
            return userRepository.save(user);
        }
    }

    @Override
    public UserModel update(@NonNull UserRequest userRequest) {
        return null;
    }

    @Override
    public void delete(@NonNull String id) {
        userRepository.delete(getOneById(id));

    }

    @Override
    public UserModel getOneByEmailAndPassword(@NonNull String email, @NonNull String password) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getOneByEmail(username);
    }
}
