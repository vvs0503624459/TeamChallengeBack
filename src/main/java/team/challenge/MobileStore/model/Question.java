package team.challenge.MobileStore.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Set;

@Data
@Document
public class Question {
    @Id
    private String id;
    @DocumentReference
    private Comment question;
    private Set<String> photosUri;
}
