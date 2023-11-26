package team.challenge.MobileStore.service;

import lombok.NonNull;

import java.util.List;

public interface CatalogService {
    List<?> getAll();
    CatalogResponse getOneById(@NonNull final String id);
    void delete(@NonNull final String catalogId);
    CatalogResponse create(@NonNull final CatalogRequest catalogRequest);
    CatalogResponse update(@NonNull final String id, @NonNull final CatalogRequest catalogRequest);

}
