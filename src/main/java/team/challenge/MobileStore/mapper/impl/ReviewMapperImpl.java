package team.challenge.MobileStore.mapper.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.challenge.MobileStore.dto.ReviewMarkDto;
import team.challenge.MobileStore.dto.ReviewResponse;
import team.challenge.MobileStore.mapper.ReviewMapper;
import team.challenge.MobileStore.model.Likes;
import team.challenge.MobileStore.model.Review;
import team.challenge.MobileStore.service.ReviewService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewMapperImpl implements ReviewMapper {
    private final ReviewService reviewService;
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

    @Override
    public ReviewMarkDto mapToReviewMarkDto(@NonNull List<Review> reviews) {
        Integer ratingSum = reviews.stream().map(Review::getRating).reduce(0, Integer::sum);
        return new ReviewMarkDto((double) ratingSum/ reviews.size(), reviews.size());
    }

    @Override
    public List<ReviewResponse> mapToReviewResponseList(List<Review> reviews) {
        return reviews.stream().map(this::mapToReviewResponse).toList();
    }
}
