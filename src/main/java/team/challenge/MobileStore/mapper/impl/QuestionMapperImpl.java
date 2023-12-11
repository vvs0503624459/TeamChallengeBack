package team.challenge.MobileStore.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.challenge.MobileStore.dto.QuestionResponse;
import team.challenge.MobileStore.mapper.CommentMapper;
import team.challenge.MobileStore.mapper.QuestionMapper;
import team.challenge.MobileStore.model.Question;

import java.util.List;
@Component
@RequiredArgsConstructor
public class QuestionMapperImpl implements QuestionMapper {
    private final CommentMapper commentMapper;
    @Override
    public QuestionResponse mapToResponse(Question question) {
        return new QuestionResponse(question.getId(), commentMapper.mapToResponse(question.getQuestion()), question.getPhotosUri());
    }

    @Override
    public List<QuestionResponse> mapToResponseList(List<Question> questions) {
        return questions.stream().map(this::mapToResponse).toList();
    }
}
