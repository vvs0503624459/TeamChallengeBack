package team.challenge.MobileStore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document("cart_item")
public class CartItem {


    @Id
    private String id;

    @DocumentReference
    private String deviceId;

    private int quantity;

}
