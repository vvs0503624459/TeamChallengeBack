package team.challenge.MobileStore.model;

import lombok.Data;

import java.util.List;

@Data
public class CatalogueGroupSpecification {

    private String nameOfGroup;

    private List<String> elementsInGroup;

}
//{$and: [{"_id": ObjectId("65759b9b60e60a1595806c70")}, {'groupSpecifications' : {$elemMatch: {'nameOfGroup': 'Apple'}}}]}
