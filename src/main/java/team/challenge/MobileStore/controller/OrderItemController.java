package team.challenge.MobileStore.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.challenge.MobileStore.model.OrderItem;
import team.challenge.MobileStore.service.OrderItemService;

@RestController
@RequestMapping("/api/v1/order-items")
@AllArgsConstructor
public class OrderItemController {

    private OrderItemService orderItemService;

    @GetMapping("/{deviceId}/{orderId}")
    public ResponseEntity<OrderItem> addOrderItem(
            @PathVariable String deviceId,
            @PathVariable String orderId,
            @RequestParam(defaultValue = "1") int quantity
    ) {
        return ResponseEntity.ok(orderItemService.addOrderItem(orderId, deviceId, quantity));
    }

    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable String orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);
        return ResponseEntity.ok("Item was deleted");
    }
}
