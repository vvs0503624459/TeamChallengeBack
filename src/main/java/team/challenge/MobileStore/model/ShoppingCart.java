package team.challenge.MobileStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {

    @Id
    private String id;

    private CartItem cartItem;

    private LocalDateTime createdAt;

    private int price;

    private DeliveryType deliveryType;

    private PaymentType paymentType;

    private CartStatus cartStatus;

}