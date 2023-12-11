package team.challenge.MobileStore.dto;

import team.challenge.MobileStore.model.CatalogueGroupSpecification;

import java.util.List;

public record CatalogueRequest(
        String title,
        List<CatalogueGroupSpecification> groupSpecifications
) {
}
