package team.challenge.MobileStore.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.challenge.MobileStore.dto.CommentRequest;
import team.challenge.MobileStore.dto.CommentResponse;
import team.challenge.MobileStore.mapper.CommentMapper;
import team.challenge.MobileStore.model.Comment;
import team.challenge.MobileStore.model.UserModel;
import team.challenge.MobileStore.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class CommentMapperImpl implements CommentMapper {
    private final UserService userService;
    @Override
    public Comment mapToModel(CommentRequest dto) {
        LocalDateTime time = LocalDateTime.now();
        UserModel user = userService.getOneById(dto.userId());
        return Comment.builder()
                .createdDate(time)
                .author(user)
                .message(dto.message())
                .build();
    }

    @Override
    public CommentResponse mapToResponse(Comment comment) {
        if (comment == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new CommentResponse(comment.getId(), comment.getMessage(), comment.getCreatedDate().format(formatter), comment.getAuthor().getId(), mapToResponse(comment.getAnswer()));
    }
}
