package team.challenge.MobileStore.dto;

import java.util.Set;

public record QuestionResponse(
        String id,
        CommentResponse comment,
        Set<String> photosUri
) {
}
