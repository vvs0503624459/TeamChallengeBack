package team.challenge.MobileStore.mapper;

import lombok.NonNull;
import team.challenge.MobileStore.dto.ReviewResponse;
import team.challenge.MobileStore.model.Review;

public interface ReviewMapper {
    ReviewResponse mapToReviewResponse(@NonNull final Review review);
//    Review mapToReview(@NonNull final Review review);
}
