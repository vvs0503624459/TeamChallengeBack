package team.challenge.MobileStore.dto;

public record CommentResponse(
        String id,
        String message,
        String creatingDate,
        String userInfo,
        CommentResponse answer
) {
}
