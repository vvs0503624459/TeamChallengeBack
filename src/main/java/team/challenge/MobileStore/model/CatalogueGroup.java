package team.challenge.MobileStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogueGroup {

    private String nameOfGroup;

    private Map<String, String> hashtagsOfName;

    private List<CatalogueGroupSpecification> catalogueGroupSpecifications;



}