package team.challenge.MobileStore.dto;

import team.challenge.MobileStore.model.Comment;
import team.challenge.MobileStore.model.Likes;

import java.util.List;

public record ReviewRequest(
        String deviceId,
        Integer rating,
        String pluses,
        String minuses,
        Comment comment,
        List<String> tags,
        List<String> photosUri,
        String reviewerId,
        Likes like
) {
}
