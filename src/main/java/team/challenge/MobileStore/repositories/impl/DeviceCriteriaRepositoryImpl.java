package team.challenge.MobileStore.repositories.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import team.challenge.MobileStore.model.Device;
import team.challenge.MobileStore.repositories.DeviceCriteriaRepository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class DeviceCriteriaRepositoryImpl implements DeviceCriteriaRepository {
    private final MongoTemplate template;
    @Override
    public List<Device> findAll(@NonNull Query query) {
        return template.find(query, Device.class);
    }
}
