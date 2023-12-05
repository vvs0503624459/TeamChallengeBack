package team.challenge.MobileStore.mapper;

import lombok.NonNull;
import team.challenge.MobileStore.dto.DeviceDtoFull;
import team.challenge.MobileStore.dto.DeviceDtoShort;
import team.challenge.MobileStore.model.Device;

public interface DeviceMapper {
    DeviceDtoFull mapToFullDto(@NonNull Device device);
    DeviceDtoShort mapToShortDto(@NonNull Device device);
}
