package team.challenge.MobileStore.controller;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import team.challenge.MobileStore.dto.mainPage.DeviceDtoShort;
import team.challenge.MobileStore.model.Device;
import team.challenge.MobileStore.service.DeviceService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Main controller to get all devices, create, update, delete
 */
@RestController
@RequestMapping("/api/v1/devises")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;


    /**
     * @param id - unique id of device
     * @return - json file with device by his id.
     */
    @GetMapping("/{id}")
    public DeviceDtoFull getDeviceById(@PathVariable String id) {
        return ResponseEntity.ok(this.deviceService.getOneById(id));
    }

    /**
     * @param size - size of phone
     * @param brandId - brand id of phone
     * @param catalogId - catalog id of phone
     * @return - json file with list devices
     */
    @GetMapping()
    public ResponseEntity<List<DeviceDtoShort>> getAllWithSizeFromCatalogAndBrand(
            @RequestParam(name = "size", required = false) Optional<Integer> size,
            @RequestParam(name = "brandId", required = false) Optional<String> brandId,
            @RequestParam(name = "catalogId", required = false) Optional<String> catalogId) {
        if (size.isPresent() && brandId.isPresent() && catalogId.isPresent()) {
            return ResponseEntity.ok(this.deviceService.getAllWithSizeFromCatalogAndBrand(size.get(), brandId.get(), catalogId.get()));
        } else if (brandId.isPresent() && catalogId.isPresent()) {
            return ResponseEntity.ok(this.deviceService.getAllByBrandAndCatalogue(brandId.get(), catalogId.get()));
        } else if (size.isPresent()) {
            return ResponseEntity.ok(this.deviceService.getAllWithSize(size.get()));
        } else {
            return ResponseEntity.ok(this.deviceService.getAll());
        }
    }

    /**
     * @param id - unique id of phone
     * @return status of action
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDeviceById(@PathVariable String id) {
        return ResponseEntity.ok(this.deviceService.delete(id));
    }

    /**
     * @param deviceRequest - information about phone that was put by user
     * @return json file with all information about created phone
     */
    @PostMapping
    public ResponseEntity<DeviceDtoFull> createDevice(@RequestBody DeviceRequest deviceRequest) {
        return ResponseEntity.ok(this.deviceService.create(deviceRequest));
    }

    /**
     * @param id - unique id of phone
     * @param deviceRequest - updated device
     * @return json file with all information about updated phone
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDeviceById(@PathVariable String id, @RequestBody DeviceRequest deviceRequest) {
        return ResponseEntity.ok(this.deviceService.update(id, deviceRequest));
    }
}