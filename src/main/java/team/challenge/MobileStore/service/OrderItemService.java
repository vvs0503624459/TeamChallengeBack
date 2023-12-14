package team.challenge.MobileStore.service;

import team.challenge.MobileStore.model.OrderItem;

public interface OrderItemService {

    OrderItem addOrderItem(String orderId, String deviceId, int quantity);

    void deleteOrderItem(String orderItemId);
}