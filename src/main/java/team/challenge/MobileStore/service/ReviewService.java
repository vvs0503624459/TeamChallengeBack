package team.challenge.MobileStore.service;

import lombok.NonNull;
import team.challenge.MobileStore.dto.CommentRequest;
import team.challenge.MobileStore.dto.ReviewRequest;
import team.challenge.MobileStore.model.Likes;
import team.challenge.MobileStore.model.Review;

import java.util.List;
import java.util.Set;

/**
 * Review service with CRUD operations.
 */
public interface ReviewService {
    /**
     * @param size returned list size.
     * @param deviceId device ID to find reviews.
     * @return list with reviews which contain present device ID with present size.
     */
    List<Review> getAllByDevice(@NonNull final String deviceId);

    /**
     * @param id unique review ID.
     * @return DTO with information about review you were looking for.
     */
    Review getOne(@NonNull final String id);


    /**
     * @param reviewRequest DTO with information about review.
     * @return DTO with information about created review.
     */
    Review create(@NonNull final ReviewRequest reviewRequest);

    /**
     * @param id unique review ID to update.
     * @param reviewRequest DTO with new information for given review.
     * @return DTO with information about updated review.
     */
    Review update(@NonNull final String id, @NonNull final ReviewRequest reviewRequest);

    /**
     * @param id unique review ID to delete.
     */
    void delete(@NonNull final String id);


    /**
     * @param deviceId device ID to find tags.
     * @return set of device tags.
     */
    Set<String> getDeviceTags(@NonNull final String deviceId);

    /**
     * @param userId user ID who adds mark.
     * @param reviewId review ID to which a mark is adding.
     * @param like like or dislike.
     */
    Review giveLike(@NonNull final String userId, @NonNull final String reviewId, @NonNull final Likes like);

    /**
     * @param userId user ID who take back mark.
     * @param reviewId review ID from which a mark is taken.
     */
    Review takeLike(@NonNull final String userId, @NonNull final String reviewId);
    Review reply(@NonNull String reviewId, @NonNull String commentId, @NonNull CommentRequest comment);
}
