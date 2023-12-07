package team.challenge.MobileStore.mapper;

import lombok.NonNull;
import team.challenge.MobileStore.dto.DeviceDtoFull;
import team.challenge.MobileStore.dto.DeviceDtoShort;
import team.challenge.MobileStore.dto.SameDeviceDto;
import team.challenge.MobileStore.model.Device;

import java.util.List;

public interface DeviceMapper {
    DeviceDtoFull mapToFullDto(@NonNull Device device);
    DeviceDtoShort mapToShortDto(@NonNull Device device);
    List<DeviceDtoShort> mapToDeviceShortDtoList(List<Device> devices);

    List<SameDeviceDto> mapToSameDeviceDtoList(List<Device> devices);
    SameDeviceDto mapToSameDeviceDto(Device device);

}
