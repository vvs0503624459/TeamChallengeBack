package team.challenge.MobileStore.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import team.challenge.MobileStore.model.Order;

public interface ShoppingCartRepository extends MongoRepository<Order, String> {

}
