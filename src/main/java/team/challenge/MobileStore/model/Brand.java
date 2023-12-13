package team.challenge.MobileStore.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand {
    @Id
    private String id;
    @Indexed(unique = true)
    private String title;
    private String ulrLogoBrand;

    public Brand(String title) {
        this.title = title;
    }
}