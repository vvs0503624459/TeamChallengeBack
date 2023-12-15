package team.challenge.MobileStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


@Document(collection = "order_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {

    @Id
    private String id;

    @DocumentReference
    private Device device;

    private int quantity;

    public OrderItem(Device device, int quantity) {
        this.device = device;
        this.quantity = quantity;
    }
}
