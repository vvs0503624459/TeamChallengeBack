package team.challenge.MobileStore.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

/**
 * Model with information about device
 **/


@Document
@Data
public class Device {
    @Id
    private String id;
    List<Characteristic> characteristics;
    String uriMainPhoto;
    List<String> uriPhotos;
    Integer price;
    Integer discount;
    String deviceCode;
    @DocumentReference
    List<Review> reviews;
}