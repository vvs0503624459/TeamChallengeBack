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
    @Id
    String id;
    @Indexed(unique = true)
    String title;
    @DocumentReference
    List<Brand> brands;
}
