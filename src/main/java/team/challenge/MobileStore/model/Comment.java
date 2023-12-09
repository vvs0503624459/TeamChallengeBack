package team.challenge.MobileStore.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Data
@Builder
@Document
public class Comment {
    private String id;
    private String message;
    @DocumentReference
    private Comment answer;
    @DocumentReference
    private UserModel author;
    private LocalDateTime createdDate;
}