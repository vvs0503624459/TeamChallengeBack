package team.challenge.MobileStore.service;

import lombok.NonNull;
import team.challenge.MobileStore.dto.DeviceDtoFull;
import team.challenge.MobileStore.dto.DeviceDtoShort;
import team.challenge.MobileStore.dto.SameDeviceDto;
import team.challenge.MobileStore.model.Device;

import java.util.List;

public interface DeviceService {
    List<DeviceDtoShort> getAll();
    List<DeviceDtoShort> getAllByBrandAndCatalogue(@NonNull final String brand, @NonNull final String catalogue);
    List<SameDeviceDto> getAllSameDevices(@NonNull final String deviceSeries);
    List<SameDeviceDto> getAllSameDevicesByInternalMemory(@NonNull final String deviceSeries, @NonNull final String internalMemory);
    List<DeviceDtoFull> getOneById(@NonNull final String id);
    void delete(@NonNull final String id);
    Device create(@NonNull final DeviceRequest deviceRequest);
    Device update(@NonNull final String deviceId, @NonNull final DeviceRequest deviceRequest);

}
