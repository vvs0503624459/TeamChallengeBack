package team.challenge.MobileStore.dto.mainPage;

import java.math.BigDecimal;
import java.util.List;

/**
 * Dto with information that is required on the main page.
 *
 * @param id - device ID.
 * @param mainPhotoUri - URI of main photo.
 * @param reviews - DTO with mark and count of votes.
 * @param price - device price.
 * @param discount - device discount.
 * @param sameDevices - DTOs with information about same devices with different colors.
 */
public record DeviceDtoShort(
        String id,
        String mainPhotoUri,
        ReviewsShortDto reviews,
        BigDecimal price,
        Integer discount,
        List<OtherColors> sameDevices
) {
}
