package team.challenge.MobileStore.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.challenge.MobileStore.dto.CommentRequest;
import team.challenge.MobileStore.dto.QuestionRequest;
import team.challenge.MobileStore.exception.ModelNotFoundException;
import team.challenge.MobileStore.model.Device;
import team.challenge.MobileStore.model.Question;
import team.challenge.MobileStore.repositories.QuestionRepository;
import team.challenge.MobileStore.service.CommentService;
import team.challenge.MobileStore.service.DeviceService;
import team.challenge.MobileStore.service.QuestionService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final DeviceService deviceService;
    private final CommentService commentService;
    @Override
    public List<Question> getAllByDevice(@NonNull String deviceId) {
        return deviceService.getOne(deviceId).getQuestions();
    }

    @Override
    public Question getOne(@NonNull String id) {
        return questionRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(String.format("Question with id: %s not found", id)));
    }

    @Override
    public Question create(@NonNull QuestionRequest questionRequest) {
        Device device = deviceService.getOne(questionRequest.deviceId());
        String memory = device.getSpecificationValue("internal memory");
        List<Device> devices = deviceService.getAllWithSameMemory(questionRequest.deviceId(), memory);
        Question question = Question.builder()
                .photosUri(questionRequest.photosUri())
                .question(commentService.createNewComment(questionRequest.comment()))
                .build();
        question = questionRepository.save(question);
        deviceService.addQuestionToDevices(question, devices);
        return question;
    }

    @Override
    public Question update(@NonNull String id, @NonNull QuestionRequest questionRequest) {
        Question question = getOne(id);
        question.setPhotosUri(questionRequest.photosUri());
        question.setQuestion(commentService.createNewComment(questionRequest.comment()));
        return questionRepository.save(question);
    }

    @Override
    public void delete(@NonNull String id) {
        Question question = getOne(id);
        deviceService.deleteQuestionFromDevices(question);
        questionRepository.delete(question);

    }

    @Override
    public Question reply(@NonNull String questionId, @NonNull String commentId, @NonNull CommentRequest comment) {
        commentService.addAnswer(commentId, comment);
        return getOne(questionId);
    }
}
