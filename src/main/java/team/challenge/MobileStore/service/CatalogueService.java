package team.challenge.MobileStore.service;

import lombok.NonNull;
import team.challenge.MobileStore.dto.CatalogueRequest;
import team.challenge.MobileStore.model.Catalogue;

import java.util.List;

public interface CatalogueService {
    List<Catalogue> getAll();



    void delete(@NonNull final String catalogId);

    Catalogue create(@NonNull final Catalogue catalogue);

    Catalogue update(@NonNull final String id, @NonNull final Catalogue catalogue);

}
