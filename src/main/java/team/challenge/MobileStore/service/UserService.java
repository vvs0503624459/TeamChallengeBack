package team.challenge.MobileStore.service;

import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetailsService;
import team.challenge.MobileStore.dto.UserRequest;
import team.challenge.MobileStore.model.UserModel;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<?> getAll();
    UserModel getOneById(@NonNull final String id);
    UserModel getOneByEmail(@NonNull final String email);
    UserModel create(@NonNull final UserRequest userRequest);
    UserModel update(@NonNull final UserRequest userRequest);
    void delete(@NonNull final String id);
    UserModel getOneByEmailAndPassword(@NonNull final String email, @NonNull final String password);

}
