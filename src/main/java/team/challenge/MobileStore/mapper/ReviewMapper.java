package team.challenge.MobileStore.mapper;

import lombok.NonNull;
import team.challenge.MobileStore.dto.ReviewMarkDto;
import team.challenge.MobileStore.dto.ReviewResponse;
import team.challenge.MobileStore.model.Review;

import java.util.List;

public interface ReviewMapper {
    ReviewResponse mapToReviewResponse(@NonNull final Review review);
    /**
     * @param reviews list with reviews to calculate and map.
     * @return DTO with mark and count of votes.
     */
    ReviewMarkDto mapToReviewMarkDto(@NonNull final List<Review> reviews);
//    Review mapToReview(@NonNull final Review review);
}
