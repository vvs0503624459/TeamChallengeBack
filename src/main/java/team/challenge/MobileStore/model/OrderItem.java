package team.challenge.MobileStore.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


@Document(collection = "order_item")
public class OrderItem {

    String id;
    @DocumentReference
    private Device device;

    private int quantity;

    @DocumentReference
    private Order order;

}
