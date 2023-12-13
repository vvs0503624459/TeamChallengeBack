package team.challenge.MobileStore.service;

import lombok.NonNull;
import team.challenge.MobileStore.dto.BrandRequest;
import team.challenge.MobileStore.model.Brand;
import team.challenge.MobileStore.model.Catalogue;

import java.util.List;


public interface BrandService {
    List<Brand> getAll();
    Brand getOneById(@NonNull final String id);
    Brand getOneByTitle(@NonNull final String title);
    void delete(@NonNull String brandId);
    Brand create(@NonNull final BrandRequest brandRequest);
    Brand update(@NonNull final String brandId, @NonNull final BrandRequest brandRequest);
}
