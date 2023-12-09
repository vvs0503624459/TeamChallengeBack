package team.challenge.MobileStore.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.challenge.MobileStore.exception.ModelAlreadyExistException;
import team.challenge.MobileStore.exception.ModelNotFoundException;
import team.challenge.MobileStore.model.Catalogue;
import team.challenge.MobileStore.repositories.CatalogueRepository;
import team.challenge.MobileStore.service.CatalogueService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogueServiceImpl implements CatalogueService {

    private final CatalogueRepository catalogueRepository;

    //todo

    @Override
    public List<Catalogue> getAll() {
        return (catalogueRepository.findAll());
    }


    @Override
    public void delete(@NonNull String catalogId) {
        catalogueRepository.deleteById(catalogId);
    }


    @Override
    public Catalogue create(@NonNull Catalogue catalogue) {
        Catalogue catalogueByTitle = getCatalogueByTitle(catalogue.getTitle());
        if (catalogueByTitle != null) {
            throw new ModelAlreadyExistException(String.format("Catalogue with this title: %s already exists", catalogue.getId()));
        } else {
            return catalogueRepository.save(catalogue);
        }
    }

    @Override
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
