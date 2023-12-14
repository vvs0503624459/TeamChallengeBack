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
import team.challenge.MobileStore.service.OrderItemService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemRepository orderItemRepository;

    private DeviceRepository deviceRepository;

    private MongoTemplate mongoTemplate;


    @Override
    public OrderItem addOrderItem(String orderId, String deviceId, int quantity) {
        Optional<Device> device = deviceRepository.findById(deviceId);

        if (device.isPresent()) {
            OrderItem orderItem = orderItemRepository.save(new OrderItem(device.get(), quantity));

            mongoTemplate.update(Order.class)
                    .matching(Criteria.where("_id").is(orderId))
                    .apply(new Update().push("orderItems").value(orderItem))
                    .first();

            return orderItem;
        } else {
            throw new ModelNotFoundException(String.format("Model with such id %s doesn't exist", deviceId));
        }
    }

    @Override
    public void deleteOrderItem(String orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }

}
