package team.challenge.MobileStore.service;

import lombok.NonNull;
import team.challenge.MobileStore.dto.CatalogRequest;
import team.challenge.MobileStore.model.Catalogue;

import java.util.List;

public interface CatalogService {
    List<?> getAll();
    Catalogue getOneById(@NonNull final String id);
    void delete(@NonNull final String catalogId);
    Catalogue create(@NonNull final CatalogRequest catalogRequest);
    Catalogue update(@NonNull final String id, @NonNull final CatalogRequest catalogRequest);

}
