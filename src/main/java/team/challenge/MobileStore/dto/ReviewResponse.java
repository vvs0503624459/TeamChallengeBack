package team.challenge.MobileStore.dto;

import java.util.Set;

public record ReviewResponse(
        String id,
        Integer rating,
        String pluses,
        String minuses,
        CommentResponse comment,
        Set<String> tags,
        Set<String> photosUri,
        Integer countOfLikes,
        Integer countOfDislikes

) {
}
