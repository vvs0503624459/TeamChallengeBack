package team.challenge.MobileStore.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.challenge.MobileStore.dto.DeviceDtoFull;
import team.challenge.MobileStore.dto.DeviceDtoShort;
import team.challenge.MobileStore.dto.DeviceRequest;
import team.challenge.MobileStore.dto.DeviceWithSameColorDto;
import team.challenge.MobileStore.repositories.DeviceRepository;
import team.challenge.MobileStore.service.DeviceServiceFirst;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DeviceServiceFirstImpl implements DeviceServiceFirst {
    private final DeviceRepository deviceRepository;
    @Override
    public List<DeviceDtoShort> getAllWithSize(@NonNull Integer size) {
        return null;
    }

    @Override
    public List<DeviceDtoShort> getAllWithSizeFromCatalogAndBrand(@NonNull Integer size, @NonNull String brandId, @NonNull String catalogId) {
        return null;
    }

    @Override
    public List<DeviceDtoShort> getAllByBrandAndCatalogue(@NonNull String brandId, @NonNull String catalogId) {
        return null;
    }

    @Override
    public List<DeviceWithSameColorDto> getAllSameDevicesWithSameColor(@NonNull String deviceId, @NonNull String color) {
        return null;
    }

    @Override
    public List<DeviceWithSameColorDto> getAllSameDevicesWithSameInternalMemory(@NonNull String deviceId, @NonNull String internalMemory) {
        return null;
    }

    @Override
    public DeviceDtoFull getOneById(@NonNull String id) {
        return null;
    }

    @Override
    public void delete(@NonNull String id) {

    }

    @Override
    public DeviceDtoFull create(@NonNull DeviceRequest deviceRequest) {
        return null;
    }

    @Override
    public DeviceDtoFull update(@NonNull String deviceId, @NonNull DeviceRequest deviceRequest) {
        return null;
    }
}
