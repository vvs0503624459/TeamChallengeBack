package team.challenge.MobileStore.service;

import lombok.NonNull;
import team.challenge.MobileStore.model.Order;

import java.util.List;

public interface ShoppingCartService {

    List<Order> getAllOrders();

    List<Order> getAllOrdersByUserId(@NonNull String userId);

    Order getOrderById(@NonNull String orderId);

    void deleteOrderById(@NonNull String orderId);

    Order createOrder(@NonNull Order order);

    Order updateOrder(String orderId, @NonNull Order updatedOrder);
}
