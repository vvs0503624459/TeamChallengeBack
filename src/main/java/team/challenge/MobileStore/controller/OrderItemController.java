package team.challenge.MobileStore.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.challenge.MobileStore.model.OrderItem;
import team.challenge.MobileStore.service.OrderItemService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/order-items")
@AllArgsConstructor
public class OrderItemController {

    private OrderItemService orderItemService;

    @GetMapping("/{deviceId}/{orderId}")
    public ResponseEntity<OrderItem> addOrderItem(
            Map<String, String> params
            ) {
        return ResponseEntity.ok(orderItemService.addOrderItem(params));
    }

    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable String orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);
        return ResponseEntity.ok("Item was deleted");
    }
}
