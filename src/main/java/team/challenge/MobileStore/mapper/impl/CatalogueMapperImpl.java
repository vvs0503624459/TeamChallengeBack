package team.challenge.MobileStore.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.challenge.MobileStore.dto.CatalogueRequest;
import team.challenge.MobileStore.dto.CatalogueResponse;
import team.challenge.MobileStore.mapper.CatalogueMapper;
import team.challenge.MobileStore.model.Catalogue;

@Component
@RequiredArgsConstructor
public class CatalogueMapperImpl implements CatalogueMapper {


    @Override
    public Catalogue mapCatalogueRequestToCatalogue(CatalogueRequest catalogueRequest) {
        Catalogue catalogue = new Catalogue();
        catalogue.setTitle(catalogue.getTitle());
        catalogue.setGroupSpecifications(catalogueRequest.groupSpecifications());
        return catalogue;
    }

    @Override
    public CatalogueResponse mapCatalogueToCatalogueResponse(Catalogue catalogue) {
        return new CatalogueResponse(
                catalogue.getId(),
                catalogue.getTitle(),
                catalogue.getGroupSpecifications()
        );
    }
}