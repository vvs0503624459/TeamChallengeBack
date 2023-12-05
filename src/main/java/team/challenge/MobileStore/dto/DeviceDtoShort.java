package team.challenge.MobileStore.dto;

import java.util.List;

/**
 * Dto with information that is required on the main page.
 *
 * @param id - device ID.
 * @param title - device title.
 * @param color - device color.
 * @param mainPhotoUri - URI of main photo.
 * @param review - DTO of review with mark and count of votes.
 * @param price - device price.
 * @param discount - device discount.
 * @param sameDevices - DTOs with information about same devices with different colors.
 */
public record DeviceDtoShort(
        String id,
        String title,
        String color,
        String mainPhotoUri,
        ReviewMarkDto review,
        Integer price,
        Integer discount,
        List<SameDeviceDto> devicesWithSameColor
) {
}
