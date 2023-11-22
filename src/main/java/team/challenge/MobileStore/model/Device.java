package team.challenge.MobileStore.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

/**
 * Model with information about device
 **/


@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    @Id
    private String id;
    private Brand brand;
    private List<SpecificationGroup> specificationGroups;
    private String uriMainPhoto;
    private List<String> uriPhotos;
    private Integer price;
    private Integer discount;
    private String deviceCode;
    private Catalog catalog;
    @DocumentReference
    private List<Review> reviews;
}