package team.challenge.MobileStore.service;

import lombok.NonNull;
import team.challenge.MobileStore.dto.CommentRequest;
import team.challenge.MobileStore.dto.QuestionRequest;
import team.challenge.MobileStore.model.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getAllByDevice(@NonNull final String deviceId);
    Question getOne(@NonNull final String id);
    Question create(@NonNull final QuestionRequest questionRequest);
    Question update(@NonNull final String id, @NonNull final QuestionRequest questionRequest);
    void delete(@NonNull final String id);
    Question reply(@NonNull final String questionId, @NonNull final String commentId, @NonNull final CommentRequest comment);
}
