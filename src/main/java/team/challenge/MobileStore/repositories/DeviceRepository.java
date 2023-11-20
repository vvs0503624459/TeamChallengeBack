package team.challenge.MobileStore.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import team.challenge.MobileStore.model.Device;

public interface DeviceRepository extends MongoRepository<Device, String> {
}
