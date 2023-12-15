package team.challenge.MobileStore.service;

import team.challenge.MobileStore.model.*;

import java.util.List;

public interface OrderService {

    Order createOrder(Customer customer, String deliveryType, String paymentType);

    List<Order> getAllOrders();

    Order updateOrder(String idOrder, Order updatedOrder);

    void deleteOrder(String orderId);

    Order getOneOrderById(String orderId);
}