package team.challenge.MobileStore.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;


/**
 * Model for device with all information about device.
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {


    /**
     * id - unique device's identifier.
     */
    @Id
    private String id;
    /**
     * uriMainPhoto - main photo of device.
     */
    private String uriMainPhoto;
    /**
     * uriPhotos - other photos of device.
     */
    private List<String> uriPhotos;
    /**
     * price - price of device.
     */
    private Integer price;
    /**
     * discount - discount for device from 0-100. Where 0 - means there isn't any discount for device.
     */
    private Integer discount;
    /**
     * Color - the device's color.
     */
    private String Color;
    /**
     * barCode - unique device's barCode, it often written at the end of the name of device. This barcode is craeted by the company of the device.
     */
    private String barCode;
    /**
     * specificationGroups - set of specifications of the phone.
     */
    private List<SpecificationGroup> specificationGroups;
    /**
     * brand - Device's brand in the real world.
     */
    @DocumentReference
    private Brand brand;
    /**
     * catalog - Device's place in the main catalog where it stores.
     */
    @DocumentReference
    private Catalog catalog;
    /**
     * reviews - it is attached to the device.
     */
    @DocumentReference
    private List<Review> reviews;
}