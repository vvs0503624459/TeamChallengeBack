package team.challenge.MobileStore.service.impl;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import team.challenge.MobileStore.model.Order;
import team.challenge.MobileStore.repositories.ShoppingCartRepository;
import team.challenge.MobileStore.service.ShoppingCartService;

import java.util.List;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public List<Order> getAllOrders() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public List<Order> getAllOrdersByUserId(@NonNull String userId) {
        return null;
    }

    @Override
    public Order getOrderById(@NonNull String orderId) {
        return null;
    }

    @Override
    public void deleteOrderById(@NonNull String orderId) {

    }

    @Override
    public Order createOrder(@NonNull Order order) {
        return null;
    }

    @Override
    public Order updateOrder(String orderId, @NonNull Order updatedOrder) {
        return null;
    }


}