package team.challenge.MobileStore.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
@NoArgsConstructor
public class CatalogueGroups {

    private String id;

    @Indexed(unique = true)
    private String title;

    /**
     * groupSpecifications - groups of specifications for device in catalogue
     */

    private List<CatalogueGroupSpecification> groupSpecifications;

}
