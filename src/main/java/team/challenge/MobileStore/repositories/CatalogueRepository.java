package team.challenge.MobileStore.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import team.challenge.MobileStore.model.Catalogue;
import team.challenge.MobileStore.model.CatalogueGroupSpecification;

@Repository
public interface CatalogueRepository extends MongoRepository<Catalogue, String> {
    Catalogue findByTitle(String title);
}