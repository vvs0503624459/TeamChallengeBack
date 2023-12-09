package team.challenge.MobileStore.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collation = "catalogues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
     * title - name of position in header catalogue
     */
    @Indexed(unique = true)
    private String title;

    /**
     * groupSpecifications - groups of specifications for device in catalogue
     */

    private List<CatalogueGroupSpecification> groupSpecifications;

}