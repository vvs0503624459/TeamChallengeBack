package team.challenge.MobileStore.dto;

import team.challenge.MobileStore.model.CatalogueGroup;

import java.util.List;

public record CatalogueResponse(
        String id,
        String title,
        List<CatalogueGroup> groupSpecifications
) {
}
