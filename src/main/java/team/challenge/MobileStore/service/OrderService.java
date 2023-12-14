package team.challenge.MobileStore.service;

import team.challenge.MobileStore.model.*;

import java.util.List;

public interface OrderService {

    Order createOrder(Customer customer, DeliveryType deliveryType, PaymentType paymentType, OrderStatus orderStatus);

    List<Order> getAllOrders();

    Order updateOrder(String idOrder, Order updatedOrder);

    void deleteOrder(String orderId);

}