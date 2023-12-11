package team.challenge.MobileStore.mapper;

import team.challenge.MobileStore.dto.CatalogueRequest;
import team.challenge.MobileStore.dto.CatalogueResponse;
import team.challenge.MobileStore.model.Catalogue;

public interface CatalogueMapper {

    Catalogue mapCatalogueRequestToCatalogue(CatalogueRequest catalogueRequest);

    CatalogueResponse mapCatalogueToCatalogueResponse(Catalogue catalogue);
}
