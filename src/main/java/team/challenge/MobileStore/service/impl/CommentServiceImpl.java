package team.challenge.MobileStore.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.challenge.MobileStore.dto.CommentRequest;
import team.challenge.MobileStore.exception.ModelNotFoundException;
import team.challenge.MobileStore.mapper.CommentMapper;
import team.challenge.MobileStore.model.Comment;
import team.challenge.MobileStore.repositories.CommentRepository;
import team.challenge.MobileStore.service.CommentService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getOneById(@NonNull String id) {
        return commentRepository.findById(id).orElseThrow(()-> new ModelNotFoundException(String.format("Comment with id: %s not found", id)));
    }

    @Override
    public Comment createNewComment(@NonNull CommentRequest commentRequest) {
        return commentRepository.save(commentMapper.mapToModel(commentRequest));
    }

    @Override
    public Comment addAnswer(@NonNull String commentID, @NonNull CommentRequest answerRequest) {
        Comment commentFromDB = getOneById(commentID);
        commentFromDB.setAnswer(createNewComment(answerRequest));
        return commentRepository.save(commentFromDB);
    }

    @Override
    public Void delete(@NonNull String id) {
        commentRepository.delete(getOneById(id));
        return null;
    }
}
