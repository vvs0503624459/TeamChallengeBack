package team.challenge.MobileStore.service;

import lombok.NonNull;
import team.challenge.MobileStore.dto.CommentRequest;
import team.challenge.MobileStore.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();
    Comment getOneById(@NonNull final String id);
    Comment createNewComment(@NonNull CommentRequest commentRequest);
    Comment addAnswer(@NonNull String commentID, @NonNull CommentRequest answerRequest);
    Void delete(@NonNull String id);
}
