package team.challenge.MobileStore.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import team.challenge.MobileStore.model.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
