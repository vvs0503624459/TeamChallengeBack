package team.challenge.MobileStore.model;

import lombok.Data;

import java.util.List;

@Data
public class CatalogueGroupSpecification {

    private String nameOfGroup;

    private List<String> elementsInGroup;

}
