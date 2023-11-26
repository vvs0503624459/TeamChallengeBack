package team.challenge.MobileStore.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import team.challenge.MobileStore.model.Question;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
}
