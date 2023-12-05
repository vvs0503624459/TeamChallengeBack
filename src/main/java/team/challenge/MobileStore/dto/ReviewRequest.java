package team.challenge.MobileStore.dto;

import team.challenge.MobileStore.model.Comment;
import team.challenge.MobileStore.model.Likes;

import java.util.List;
import java.util.Set;

public record ReviewRequest(
        String deviceId,
        Integer rating,
        String pluses,
        String minuses,
        Comment comment,
        Set<String> tags,
        Set<String> photosUri
) {
}
