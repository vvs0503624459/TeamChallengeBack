package team.challenge.MobileStore.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "catalogue")
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
     * title - unique name of Catalogue group in header
     */

    @Indexed(unique = true)
    private String title;

    /**
     * groupSpecifications - groups of specifications for device in catalogue
     */

    private List<CatalogueGroupSpecification> groupSpecifications;

}
