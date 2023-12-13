package team.challenge.MobileStore.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.challenge.MobileStore.dto.BrandRequest;
import team.challenge.MobileStore.dto.BrandResponse;
import team.challenge.MobileStore.exception.ModelAlreadyExistException;
import team.challenge.MobileStore.exception.ModelNotFoundException;
import team.challenge.MobileStore.model.Brand;
import team.challenge.MobileStore.model.Catalogue;
import team.challenge.MobileStore.repositories.BrandRepository;
import team.challenge.MobileStore.service.BrandService;

import java.util.List;
@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }
    @Override
    public Brand getOneById(@NonNull String id) {
        return brandRepository.findById(id).orElseThrow(()-> new ModelNotFoundException(String.format("Brand with id %s not found!", id)));
    }

    @Override
    public Brand getOneByTitle(@NonNull String title) {
        return brandRepository.findByTitle(title).orElseThrow(()-> new ModelNotFoundException(String.format("Brand with title %s not found!", title)));
    }


    @Override
    public void delete(@NonNull String brandId) {
        brandRepository.delete(getOneById(brandId));

    }

    @Override
    public Brand create(@NonNull BrandRequest brandRequest) {
        try{
            getOneByTitle(brandRequest.title());
            throw new ModelAlreadyExistException(String.format("Brand with title %s is already exist!", brandRequest.title()));
        } catch (ModelNotFoundException e){
            Brand brand = Brand.builder()
                    .title(brandRequest.title())
                    .ulrLogoBrand(brandRequest.picture())
                    .build();
            return brandRepository.save(brand);
        }
    }

    @Override
    public Brand update(@NonNull String brandId, @NonNull BrandRequest brandRequest) {
        Brand brandToUpdate = getOneById(brandId);
        try{
            String existBrandID= getOneByTitle(brandRequest.title()).getId();
            if (brandToUpdate.getId().equals(existBrandID)){
                throw new ModelNotFoundException("");
            } else {
                throw new ModelAlreadyExistException(String.format("Brand with title %s is already exist!", brandRequest.title()));
            }
        }catch (ModelNotFoundException e){
            brandToUpdate.setTitle(brandRequest.title());
            brandToUpdate.setUlrLogoBrand(brandRequest.picture());
            return brandRepository.save(brandToUpdate);
        }
    }
}
