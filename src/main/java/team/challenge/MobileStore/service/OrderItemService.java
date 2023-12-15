package team.challenge.MobileStore.service;

import team.challenge.MobileStore.model.OrderItem;

import java.util.Map;

public interface OrderItemService {

    OrderItem addOrderItem(Map<String, String> params);

    void deleteOrderItem(String orderItemId);
}