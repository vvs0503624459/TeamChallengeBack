package team.challenge.MobileStore.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team.challenge.MobileStore.exception.ModelNotFoundException;
import team.challenge.MobileStore.model.*;
import team.challenge.MobileStore.repositories.OrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Customer customer, DeliveryType deliveryType, PaymentType paymentType, OrderStatus orderStatus) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setCreatedAt(LocalDateTime.now());
        order.setOrderItems(new ArrayList<>());
        order.setPrice(0);
        order.setDeliveryType(deliveryType);
        order.setPaymentType(paymentType);
        order.setOrderStatus(OrderStatus.Added);

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(String idOrder, Order updatedOrder) {
        Order order = getOneOrderById(idOrder);
        order.setCustomer(updatedOrder.getCustomer());
        order.setOrderItems(updatedOrder.getOrderItems());
        order.setPrice(updatedOrder.getPrice());
        order.setDeliveryType(updatedOrder.getDeliveryType());
        order.setPaymentType(updatedOrder.getPaymentType());
        order.setOrderStatus(updatedOrder.getOrderStatus());
        return order;
    }

    @Override
    public void deleteOrder(String orderId) {
        orderRepository.deleteById(orderId);
    }

    private Order getOneOrderById(String orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new ModelNotFoundException(String.format("Model with such an id %s wasn't found", orderId)));
    }
}