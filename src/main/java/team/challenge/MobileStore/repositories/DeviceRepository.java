package team.challenge.MobileStore.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import team.challenge.MobileStore.model.Device;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {

}