package team.challenge.MobileStore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.challenge.MobileStore.exception.ApiError;
import team.challenge.MobileStore.model.OrderItem;
import team.challenge.MobileStore.service.OrderItemService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/order-items")
@AllArgsConstructor
public class OrderItemController {

    private OrderItemService orderItemService;

    @Operation(summary = "Add one order item to order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Create one order item"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Order item wasn't created",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    }),
    })
    @GetMapping
    public ResponseEntity<OrderItem> addOrderItem(
            Map<String, String> params
            ) {
        return ResponseEntity.ok(orderItemService.addOrderItem(params));
    }

    @Operation(summary = "Delete one order item by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Delete one order item by ID"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order item with present ID not found!",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable String orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);
        return ResponseEntity.ok("Item was deleted");
    }
}
