package team.challenge.MobileStore.service;

import lombok.NonNull;
import team.challenge.MobileStore.model.Catalog;

import java.util.List;

public interface BrandService {
    List<?> getAll();
    List<?> getAllByCatalog(@NonNull final Catalog catalog);
    BrandResponce getOne(@NonNull final String id);
    void delete(@NonNull String brandId);
    BrandResponce create(@NonNull final BrandRequest brandRequest);
    BrandResponce update(@NonNull final String brandId, @NonNull final BrandRequest brandRequest);
}
