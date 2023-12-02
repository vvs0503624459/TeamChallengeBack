package team.challenge.MobileStore.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import team.challenge.MobileStore.dto.DeviceDtoFull;
import team.challenge.MobileStore.dto.SameDeviceDto;
import team.challenge.MobileStore.dto.mainPage.DeviceDtoShort;

import java.util.List;

public interface DeviceService {
    List<DeviceDtoShort> getAll();

    List<DeviceDtoShort> getAllWithSize(@NonNull final Integer size);

    List<DeviceDtoShort> getAllWithSizeFromCatalogAndBrand(@NonNull final Integer size, @NonNull final String brandId, @NonNull final String catalogId);

    List<DeviceDtoShort> getAllByBrandAndCatalogue(@NonNull final String brand, @NonNull final String catalogue);

    List<SameDeviceDto> getAllSameDevices(@NonNull final String deviceSeries);

    List<SameDeviceDto> getAllSameDevicesByInternalMemory(@NonNull final String deviceSeries, @NonNull final String internalMemory);

    DeviceDtoFull getOneById(@NonNull final String id);

    void delete(@NonNull final String id);

    DeviceDtoFull create(@NonNull final DeviceRequest deviceRequest);

    DeviceDtoFull update(@NonNull final String deviceId, @NonNull final DeviceRequest deviceRequest);
}
