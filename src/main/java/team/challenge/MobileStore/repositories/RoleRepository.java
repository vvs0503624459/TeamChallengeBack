package team.challenge.MobileStore.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import team.challenge.MobileStore.model.RoleModel;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<RoleModel, String> {
    Optional<RoleModel> findByRoleName(String roleName);
}
