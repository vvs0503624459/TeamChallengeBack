package team.challenge.MobileStore.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.challenge.MobileStore.dto.ReviewMarkDto;
import team.challenge.MobileStore.dto.ReviewRequest;
import team.challenge.MobileStore.dto.ReviewResponse;
import team.challenge.MobileStore.exception.ModelNotFoundException;
import team.challenge.MobileStore.mapper.ReviewMapper;
import team.challenge.MobileStore.model.Likes;
import team.challenge.MobileStore.model.Review;
import team.challenge.MobileStore.repositories.ReviewRepository;
import team.challenge.MobileStore.repositories.UserRepository;
import team.challenge.MobileStore.service.ReviewService;

import java.util.*;

/**
 * ReviewService implementation.
 */
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final UserRepository userRepository;
    @Override
    public List<ReviewResponse> getAllByDevice(Integer size, @NonNull String deviceId) {
        List<ReviewResponse> reviewResponses = reviewRepository.findAllByDeviceId(deviceId).stream().map(reviewMapper::mapToReviewResponse).toList();
        return size == null ? reviewResponses : reviewResponses.subList(0, size);
    }

    @Override
    public ReviewResponse getOne(@NonNull String id) {
        Review review = getReviewModel(id);
        return reviewMapper.mapToReviewResponse(review);
    }

    private Review getReviewModel(String id) {
        return reviewRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(String.format("Review with id: %s not found", id)));
    }

    @Override
    public ReviewResponse create(@NonNull ReviewRequest reviewRequest) {
        Review review = new Review();
        review.setRating(reviewRequest.rating());
        review.setPluses(reviewRequest.pluses());
        review.setMinuses(reviewRequest.minuses());
        review.setMessage(reviewRequest.comment());
        review.setTags(reviewRequest.tags());
        review.setPhotosUri(reviewRequest.photosUri());
        review.setLikesAndDislikes(new HashMap<>());
        /*
            get device
            find same by series and memory
            create review model
            build model
            map tp res
         */
        return reviewMapper.mapToReviewResponse(review);
    }

    @Override
    public ReviewResponse update(@NonNull String id, @NonNull ReviewRequest reviewRequest) {
        Review review = getReviewModel(id);
        review.setRating(reviewRequest.rating());
        review.setPluses(reviewRequest.pluses());
        review.setMinuses(reviewRequest.minuses());
        review.setMessage(reviewRequest.comment());
        review.setTags(reviewRequest.tags());
        review.setPhotosUri(reviewRequest.photosUri());
        review.setLikesAndDislikes(new HashMap<>());
        return reviewMapper.mapToReviewResponse(review);
    }

    @Override
    public void delete(@NonNull String id) {
        getOne(id);
        reviewRepository.deleteById(id);
    }

    @Override
    public ReviewMarkDto getDeviceMark(@NonNull String deviceId) {
        List<ReviewResponse> responses = getAllByDevice(null, deviceId);
        Integer ratingSum = responses.stream().map(ReviewResponse::rating).reduce(0, Integer::sum);
        return new ReviewMarkDto(((double) ratingSum/responses.size()), responses.size());
    }

    @Override
    public Set<String> getDeviceTags(@NonNull String deviceId) {
        Set<String> tags = new HashSet<>();
        for (ReviewResponse res: getAllByDevice(null, deviceId)) {
            tags.addAll(res.tags());
        }
        return tags;
    }

    @Override
    public void giveLike(@NonNull String userId, @NonNull String reviewId, @NonNull Likes like) {
        Review review = getReviewModel(reviewId);
        userRepository.findById(userId).orElseThrow(() -> new ModelNotFoundException(String.format("User with id: %s not found", userId)));
        if (!review.getLikesAndDislikes().containsKey(userId)){
            review.getLikesAndDislikes().put(userId, like);
        }
        reviewRepository.save(review);
    }

    @Override
    public void takeLike(@NonNull String userId, @NonNull String reviewId) {
        Review review = getReviewModel(reviewId);
        userRepository.findById(userId).orElseThrow(() -> new ModelNotFoundException(String.format("User with id: %s not found", userId)));
        review.getLikesAndDislikes().remove(userId);
        reviewRepository.save(review);
    }
}
