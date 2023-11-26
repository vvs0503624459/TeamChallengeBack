package team.challenge.MobileStore.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Question {
    @Id
    private String id;
    private Comment question;
}
