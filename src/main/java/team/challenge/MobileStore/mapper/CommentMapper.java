package team.challenge.MobileStore.mapper;

import team.challenge.MobileStore.dto.CommentRequest;
import team.challenge.MobileStore.dto.CommentResponse;
import team.challenge.MobileStore.model.Comment;

public interface CommentMapper {
    Comment mapToModel(CommentRequest dto);
    CommentResponse mapToResponse(Comment comment);
}
