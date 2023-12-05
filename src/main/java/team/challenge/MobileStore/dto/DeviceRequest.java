package team.challenge.MobileStore.dto;

import team.challenge.MobileStore.model.Brand;
import team.challenge.MobileStore.model.Catalogue;
import team.challenge.MobileStore.model.SpecificationGroup;

import java.util.List;

public record DeviceRequest(
        List<String> photosUri,
        Integer price,
        Integer discount,
        String skuCode,
        List<DevicePresentation> presentation,
        List<SpecificationGroup> specificationGroups,
        Brand brand,
        Catalogue catalogue

) {
}
