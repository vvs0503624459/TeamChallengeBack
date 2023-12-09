package team.challenge.MobileStore.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import team.challenge.MobileStore.model.Catalogue;

@Repository
public interface CatalogueRepository extends MongoRepository<Catalogue, String> {

    Catalogue findByTitle(String title);
}