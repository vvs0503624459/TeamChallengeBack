package team.challenge.MobileStore.service;

import lombok.NonNull;
import team.challenge.MobileStore.dto.ReviewMarkDto;
import team.challenge.MobileStore.dto.ReviewRequest;
import team.challenge.MobileStore.dto.ReviewResponse;

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
    List<?> getAllByDevice(@NonNull final Integer size, @NonNull final String deviceId);

    /**
     * @param id unique review ID.
     * @return DTO with information about review you were looking for.
     */
    ReviewResponse getOne(@NonNull final String id);


    /**
     * @param reviewRequest DTO with information about review.
     * @return DTO with information about created review.
     */
    ReviewResponse create(@NonNull final ReviewRequest reviewRequest);

    /**
     * @param id unique review ID to update.
     * @param reviewRequest DTO with new information for given review.
     * @return DTO with information about updated review.
     */
    ReviewResponse update(@NonNull final String id, @NonNull final ReviewRequest reviewRequest);

    /**
     * @param id unique review ID to delete.
     */
    void delete(@NonNull final String id);

    /**
     * @param deviceId device ID to calculate the mark.
     * @return DTO with mark and count of votes.
     */
    ReviewMarkDto getDeviceMark(@NonNull final String deviceId);

    /**
     * @param deviceId device ID to find tags.
     * @return set of device tags.
     */
    Set<String> getDeviceTags(@NonNull final String deviceId);
}
