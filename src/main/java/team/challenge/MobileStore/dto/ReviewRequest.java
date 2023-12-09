package team.challenge.MobileStore.dto;

import java.util.Set;

public record ReviewRequest(
        String deviceId,
        Integer rating,
        String pluses,
        String minuses,
        CommentRequest comment,
        Set<String> tags,
        Set<String> photosUri
) {
}
