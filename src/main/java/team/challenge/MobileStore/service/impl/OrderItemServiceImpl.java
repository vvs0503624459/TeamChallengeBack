package team.challenge.MobileStore.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import team.challenge.MobileStore.exception.ModelNotFoundException;
import team.challenge.MobileStore.model.Device;
import team.challenge.MobileStore.model.Order;
import team.challenge.MobileStore.model.OrderItem;
import team.challenge.MobileStore.repositories.DeviceRepository;
import team.challenge.MobileStore.repositories.OrderItemRepository;
import team.challenge.MobileStore.repositories.OrderRepository;
import team.challenge.MobileStore.service.OrderItemService;

import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemRepository orderItemRepository;

    private OrderRepository orderRepository;

    private DeviceRepository deviceRepository;

    private MongoTemplate mongoTemplate;


    @Override
    public OrderItem addOrderItem(Map<String, String> params) {
        if (params.containsKey("deviceId") && params.containsKey("orderId") && params.containsKey("quantity")) {
            String deviceId = params.get("deviceId");
            String orderId = params.get("orderId");
            int quantity = Integer.parseInt(params.get("quantity"));
            Optional<Device> device = deviceRepository.findById(deviceId);
            Optional<Order> order = orderRepository.findById(orderId);

            if (device.isPresent() && order.isPresent()) {
                OrderItem orderItem = orderItemRepository.save(new OrderItem(device.get(), quantity));

                int totalPrice = (int) (order.get().getPrice() + (orderItem.getDevice().getPrice() * ((100 - orderItem.getDevice().getDiscount()) * 0.01) * quantity));


                mongoTemplate.update(Order.class)
                        .matching(Criteria.where("_id").is(orderId))
                        .apply(
                                new Update().push("orderItems")
                                        .value(orderItem)
                                        .push("price")
                                        //додумати як ціну збільшувати, при додавані товара
                                        .value(totalPrice))
                        .first();

                return orderItem;
            } else {
                throw new ModelNotFoundException(String.format("Model with such id %s doesn't exist", deviceId));
            }
        } else {
            throw new RuntimeException("Bad request");
        }
    }

    @Override
    public void deleteOrderItem(String orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }

}
