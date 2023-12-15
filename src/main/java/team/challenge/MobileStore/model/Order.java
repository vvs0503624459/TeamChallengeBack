package team.challenge.MobileStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.List;

@Document("orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    //change name to order
    @Id
    private String id;

    private Customer customer;

    @DocumentReference
    private List<OrderItem> orderItems;

    private LocalDateTime createdAt;

    private int price;

    private DeliveryType deliveryType;

    private PaymentType paymentType;

    private OrderStatus orderStatus;

}