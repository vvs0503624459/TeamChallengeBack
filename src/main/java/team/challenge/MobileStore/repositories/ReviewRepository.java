package team.challenge.MobileStore.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import team.challenge.MobileStore.model.Review;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

}
