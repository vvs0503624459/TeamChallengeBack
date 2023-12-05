package team.challenge.MobileStore.dto;

import team.challenge.MobileStore.model.Comment;

import java.util.List;
import java.util.Set;

public record ReviewResponse(
        String id,
        Integer rating,
        String pluses,
        String minuses,
        Comment comment,
        Set<String> tags,
        Set<String> photosUri,
        Integer countOfLikes,
        Integer countOfDislikes

) {
}
