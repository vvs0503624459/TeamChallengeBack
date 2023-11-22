package team.challenge.MobileStore.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import team.challenge.MobileStore.model.Catalog;

@Repository
public interface CatalogRepository extends MongoRepository<Catalog, String> {

}