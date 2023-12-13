package team.challenge.MobileStore.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import team.challenge.MobileStore.model.Brand;

import java.util.Optional;

@Repository
public interface BrandRepository extends MongoRepository<Brand, String> {
    Optional<Brand> findByTitle(String title);

}