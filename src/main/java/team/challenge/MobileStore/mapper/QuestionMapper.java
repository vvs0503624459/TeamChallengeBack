package team.challenge.MobileStore.mapper;

import team.challenge.MobileStore.dto.QuestionResponse;
import team.challenge.MobileStore.model.Question;

import java.util.List;

public interface QuestionMapper {
    QuestionResponse mapToResponse(Question question);
    List<QuestionResponse> mapToResponseList(List<Question> questions);
}
