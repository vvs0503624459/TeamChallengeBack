package team.challenge.MobileStore.service;

import lombok.NonNull;
import team.challenge.MobileStore.model.Catalogue;
import team.challenge.MobileStore.model.CatalogueGroup;

import java.util.List;

public interface CatalogueService {
    List<Catalogue> getAll();

    Catalogue findById(@NonNull final String catalogueId);

    CatalogueGroup getCatalogGroupSpecificationByCatalogueIdAndCatalogueGroupSpecificationName(@NonNull final String catalogueId, @NonNull final String catalogueGroupSpecificationName);

    void delete(@NonNull final String catalogId);

    Catalogue create(@NonNull final Catalogue catalogue);

    Catalogue update(@NonNull final String id, @NonNull final Catalogue catalogue);

}
