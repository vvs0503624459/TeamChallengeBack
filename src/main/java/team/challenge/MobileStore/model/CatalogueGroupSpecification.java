package team.challenge.MobileStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogueGroupSpecification {

    private String title;

    private Map<String, String> hashTagsOfTitle;

}