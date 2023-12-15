package team.challenge.MobileStore.dto;

import team.challenge.MobileStore.model.CatalogueGroup;

import java.util.List;

public record CatalogueRequest(
        String title,
        List<CatalogueGroup> groupSpecifications
) {
}
