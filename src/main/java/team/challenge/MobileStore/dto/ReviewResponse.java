package team.challenge.MobileStore.dto;

import team.challenge.MobileStore.model.Comment;

import java.util.List;

public record ReviewResponse(
        String id,
        Integer rating,
        String pluses,
        String minuses,
        Comment comment,
        List<String> tags,
        List<String> photosUri,
        Integer countOfLikes,
        Integer countOfDislikes

) {
}
