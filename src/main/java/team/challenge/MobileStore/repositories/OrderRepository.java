package team.challenge.MobileStore.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import team.challenge.MobileStore.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

}