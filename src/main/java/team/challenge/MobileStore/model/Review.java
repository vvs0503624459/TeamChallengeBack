package team.challenge.MobileStore.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Document(collection = "review")
@Data
@Builder
public class Review {
    @Id
    private String id;
    private Integer rating;
    private String pluses;
    private String minuses;
    private Comment message;
//    @DocumentReference
//    private Set<Device> devices;
    private Set<String> tags;
    private Set<String> photosUri;
    private Map<String, Likes> likesAndDislikes;   //userId and like or dislike
}
