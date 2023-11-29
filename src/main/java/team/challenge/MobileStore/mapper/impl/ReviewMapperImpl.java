package team.challenge.MobileStore.mapper.impl;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import team.challenge.MobileStore.dto.ReviewResponse;
import team.challenge.MobileStore.mapper.ReviewMapper;
import team.challenge.MobileStore.model.Likes;
import team.challenge.MobileStore.model.Review;
@Component
public class ReviewMapperImpl implements ReviewMapper {
    @Override
    public ReviewResponse mapToReviewResponse(@NonNull Review review) {
        int countOfLikes = 0;
        int countOfDislikes = 0;
        for (Likes likes: review.getLikesAndDislikes().values()){
            if (likes.equals(Likes.LIKE)){
                countOfLikes++;
            } else {
                countOfDislikes++;
            }
        }
        return new ReviewResponse(review.getId(), review.getRating(), review.getPluses(), review.getMinuses(), review.getMessage(), review.getTags(), review.getPhotosUri(), countOfLikes, countOfDislikes);
    }
}
