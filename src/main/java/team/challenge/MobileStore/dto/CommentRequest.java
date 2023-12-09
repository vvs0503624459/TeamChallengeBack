package team.challenge.MobileStore.dto;

import java.time.LocalDateTime;

public record CommentRequest(
        String message,
        String userId
) {
}
