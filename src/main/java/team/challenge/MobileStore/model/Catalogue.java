package team.challenge.MobileStore.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collation = "catalogues")
@Data
@NoArgsConstructor
public class Catalogue {
    /**
     * Model for catalogue in header
     */


    /**
     * id - unique id of Catalogue from header
     */
    @Id
    private String id;


    /**
     * catalogueGroups - name of position in header catalogue
     */
    private List<CatalogueGroups> catalogueGroups;

}