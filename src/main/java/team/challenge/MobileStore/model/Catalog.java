package team.challenge.MobileStore.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model for catalogue in header
 */
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Catalog {
    /**
     * title - name of each row. Example { title : Audio }
     */
    private String title;
}