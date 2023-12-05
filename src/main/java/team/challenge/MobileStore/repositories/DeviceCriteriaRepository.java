package team.challenge.MobileStore.repositories;

import lombok.NonNull;
import org.springframework.data.mongodb.core.query.Query;
import team.challenge.MobileStore.model.Device;

import java.util.List;
import java.util.Map;

public interface DeviceCriteriaRepository {
    List<Device> findAll(@NonNull final Query query);
}
