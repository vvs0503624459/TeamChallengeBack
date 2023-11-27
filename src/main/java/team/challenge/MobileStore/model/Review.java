package team.challenge.MobileStore.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
import java.util.Map;

@Document(collection = "review")
@Data
public class Review {
    @Id
    private String id;
    private Integer rating;
    private String pluses;
    private String minuses;
    private Comment message;
    @DocumentReference
    private List<Device> devices;
    private List<String> tags;
    private List<String> photosUri;
    private Map<String, Likes> likesAndDislikes;   //userId and like or dislike
}
