package team.challenge.MobileStore.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.challenge.MobileStore.exception.ModelAlreadyExistException;
import team.challenge.MobileStore.exception.ModelNotFoundException;
import team.challenge.MobileStore.model.Catalogue;
import team.challenge.MobileStore.model.CatalogueGroupSpecification;
import team.challenge.MobileStore.model.SpecificationGroup;
import team.challenge.MobileStore.repositories.CatalogueRepository;
import team.challenge.MobileStore.service.CatalogueService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CatalogueServiceImpl implements CatalogueService {

    private final CatalogueRepository catalogueRepository;

    //todo

    @Override
    public List<Catalogue> getAll() {
        return catalogueRepository.findAll();
    }

    @Override
    public Catalogue findById(@NonNull String catalogueId) {
        return getCatalogueById(catalogueId);
    }

    @Override
    public CatalogueGroupSpecification getCatalogGroupSpecificationByCatalogueIdAndCatalogueGroupSpecificationName(@NonNull String catalogueId, @NonNull String catalogueGroupSpecificationName) {
        Catalogue catalogueById = getCatalogueById(catalogueId);
        List<CatalogueGroupSpecification> listCatalogueGroupSpecifications = catalogueById.getGroupSpecifications();
        for (CatalogueGroupSpecification catalogueGroupSpecification : listCatalogueGroupSpecifications) {
            if (catalogueGroupSpecification.getNameOfGroup().equals(catalogueGroupSpecificationName)) {
                return catalogueGroupSpecification;
            }
        }
        throw new ModelNotFoundException(String.format("Catalogue group with this name: %s wasn't found", catalogueGroupSpecificationName));
    }

    @Override
    @Transactional
    public void delete(@NonNull String catalogId) {
        if (getCatalogueById(catalogId) != null) {
            catalogueRepository.deleteById(catalogId);
        }
    }


    @Override
    @Transactional
    public Catalogue create(@NonNull Catalogue catalogue) {
        Catalogue catalogueByTitle = getCatalogueByTitle(catalogue.getTitle());
        if (catalogueByTitle != null) {
            throw new ModelAlreadyExistException(String.format("Catalogue with this title: %s already exists", catalogue.getTitle()));
        } else {
            return catalogueRepository.save(catalogue);
        }
    }

    @Override
    @Transactional
    public Catalogue update(@NonNull String id, @NonNull Catalogue catalogue) {
        Catalogue catalogueById = getCatalogueById(id);
        catalogueById.setTitle(catalogue.getTitle());
        catalogueById.setGroupSpecifications(catalogueById.getGroupSpecifications());
        return catalogue;
    }


    private Catalogue getCatalogueByTitle(String catalogueTitle) {
        return catalogueRepository.findByTitle(catalogueTitle);
    }

    private Catalogue getCatalogueById(String catalogueId) {
        return catalogueRepository.findById(catalogueId).orElseThrow(() -> new ModelNotFoundException(String.format("Catalogue with this id: %s wasn't found", catalogueId)));
    }
}