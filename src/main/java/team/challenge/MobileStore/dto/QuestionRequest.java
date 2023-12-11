package team.challenge.MobileStore.dto;

import java.util.Set;

public record QuestionRequest(
        String deviceId,
        CommentRequest comment,
        Set<String> photosUri
) {
}
