package team.challenge.MobileStore.service;

import lombok.NonNull;
import team.challenge.MobileStore.dto.DeviceDtoFull;
import team.challenge.MobileStore.dto.DeviceDtoShort;
import team.challenge.MobileStore.dto.DeviceRequest;
import team.challenge.MobileStore.dto.DeviceWithSameColorDto;

import java.util.List;

/**
 * Device service with CRUD and business operations.
 */
public interface DeviceServiceFirst {
    /**
     * @param size the size of list that will be returned.
     * @return list with present size of Device DTO with short information.
     */
    List<DeviceDtoShort> getAllWithSize(@NonNull final Integer size);

    /**
     * @param size the size of list that will be returned.
     * @param brandId Brand ID by which list will be returned.
     * @param catalogId Catalog ID by which list will be returned.
     * @return list with present size of Device DTO with short information filtered by brand and catalog.
     */
    List<DeviceDtoShort> getAllWithSizeFromCatalogAndBrand(@NonNull final Integer size, @NonNull final String brandId, @NonNull final String catalogId);

    /**
     * @param brandId Brand ID by which list will be returned.
     * @param catalogId Catalog ID by which list will be returned.
     * @return list of Device DTO with short information filtered by brand and catalog.
     */
    List<DeviceDtoShort> getAllByBrandAndCatalogue(@NonNull final String brandId, @NonNull final String catalogId);

    /**
     * @param deviceId
     * @param color
     * @return
     */
    List<DeviceWithSameColorDto> getAllSameDevicesWithSameColor(@NonNull final String deviceId, @NonNull final String color);

    /**
     * @param deviceId
     * @param internalMemory
     * @return
     */
    List<DeviceWithSameColorDto> getAllSameDevicesWithSameInternalMemory(@NonNull final String deviceId, @NonNull final String internalMemory);

    /**
     * @param id unique Device ID.
     * @return Device DTO with full information about device.
     */
    DeviceDtoFull getOneById(@NonNull final String id);

    /**
     * @param id id unique Device ID for delete.
     */
    void delete(@NonNull final String id);
    DeviceDtoFull create(@NonNull final DeviceRequest deviceRequest);
    DeviceDtoFull update(@NonNull final String deviceId, @NonNull final DeviceRequest deviceRequest);

}
