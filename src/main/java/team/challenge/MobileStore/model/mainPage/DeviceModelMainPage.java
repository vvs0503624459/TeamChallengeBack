package team.challenge.MobileStore.model.mainPage;


import team.challenge.MobileStore.dto.mainPage.OtherColors;
import team.challenge.MobileStore.dto.mainPage.ReviewsShortDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Model with information that is required on the main page.
 *
 * @param id            - device ID.
 * @param fullNameModel - fullName of model with all specifications.
 * @param mainPhotoUri  - URI of main photo.
 * @param reviews       - DTO with mark and count of votes.
 * @param price         - device price.
 * @param discount      - device discount.
 * @param sameDevices   - DTOs with information about same devices with different colors.
 */
public record DeviceModelMainPage(
        //todo чому price BidDecimal? ReviewsDTO? Багато DTO?
        String id,
        String fullNameModel,
        String mainPhotoUri,
        ReviewsShortDto reviews,
        BigDecimal price,
        Integer discount,
        List<OtherColors> sameDevices

) {
}