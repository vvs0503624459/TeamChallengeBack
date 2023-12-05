package team.challenge.MobileStore.service.impl;

import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.challenge.MobileStore.dto.ReviewMarkDto;
import team.challenge.MobileStore.dto.ReviewRequest;
import team.challenge.MobileStore.dto.ReviewResponse;
import team.challenge.MobileStore.exception.ModelNotFoundException;
import team.challenge.MobileStore.model.*;
import team.challenge.MobileStore.repositories.ReviewRepository;
import team.challenge.MobileStore.service.DeviceService;
import team.challenge.MobileStore.service.ReviewService;
import team.challenge.MobileStore.service.UserService;

import java.util.*;

@Service
@RequiredArgsConstructor

public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final DeviceService deviceService;
    private final UserService userService;
    @Override
    public List<Review> getAllByDevice(@NonNull String deviceId) {
        return deviceService.getOne(deviceId).getReviews();
    }

    @Override
    public Review getOne(@NonNull String id) {
        return reviewRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(String.format("Review with id: %s not found", id)));
    }

    @Override
    public Review create(@NonNull ReviewRequest reviewRequest) {
        /*
            get device
            find same by series and memory
            create review model
            build model
            map tp res
         */
        Device device = deviceService.getOne(reviewRequest.deviceId());
        String memory = device.getSpecificationValue("internal memory");
        List<Device> devices = deviceService.getAllWithSameMemory(reviewRequest.deviceId(), memory);
        Review review = Review.builder()
                .rating(reviewRequest.rating())
                .pluses(reviewRequest.pluses())
                .minuses(reviewRequest.minuses())
                .message(reviewRequest.comment())
                .tags(reviewRequest.tags())
                .photosUri(reviewRequest.photosUri())
                .build();
        review = reviewRepository.save(review);
        deviceService.addReviewToDevices(review, devices);
        return review;
    }

    @Override
    public Review update(@NonNull String id, @NonNull ReviewRequest reviewRequest) {
        Review review = getOne(id);
        review.setRating(reviewRequest.rating());
        review.setPluses(reviewRequest.pluses());
        review.setMinuses(reviewRequest.minuses());
        review.setMessage(reviewRequest.comment());
        review.setTags(new HashSet<>(reviewRequest.tags()));
        review.setPhotosUri(new HashSet<>(reviewRequest.photosUri()));
        review.setLikesAndDislikes(new HashMap<>());
        return reviewRepository.save(review);
    }

    @Override
    public void delete(@NonNull String id) {
        Review review = getOne(id);
        deviceService.deleteReviewFromDevices(review);
        reviewRepository.delete(review);
    }


    @Override
    public Set<String> getDeviceTags(@NonNull String deviceId) {
        Set<String> tags = new HashSet<>();
        for (Review res: getAllByDevice( deviceId)) {
            tags.addAll(res.getTags());
        }
        return tags;
    }

    @Override
    public Review giveLike(@NonNull String userId, @NonNull String reviewId, @NonNull Likes like) {
        Review review = getOne(reviewId);
        userService.getOneById(userId);
        if (!review.getLikesAndDislikes().containsKey(userId)){
            review.getLikesAndDislikes().put(userId, like);
        }
        return reviewRepository.save(review);
    }

    @Override
    public Review takeLike(@NonNull String userId, @NonNull String reviewId) {
        Review review = getOne(reviewId);
        userService.getOneById(userId);
        review.getLikesAndDislikes().remove(userId);
        return reviewRepository.save(review);
    }
}
