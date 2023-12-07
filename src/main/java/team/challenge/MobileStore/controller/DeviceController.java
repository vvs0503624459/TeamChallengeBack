package team.challenge.MobileStore.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.challenge.MobileStore.dto.DeviceDtoFull;
import team.challenge.MobileStore.dto.DeviceDtoShort;
import team.challenge.MobileStore.mapper.DeviceMapper;
import team.challenge.MobileStore.service.DeviceService;

import java.util.List;
import java.util.Map;

/**
 * Main controller to get all devices, create, update, delete
 */
@RestController
@RequestMapping("/api/v1/devises")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;
    private final DeviceMapper deviceMapper;


    /**
     * @param id - unique id of device
     * @return - json file with device by his id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DeviceDtoFull> getDeviceById(@PathVariable String id) {
        return ResponseEntity.ok(deviceMapper.mapToFullDto(deviceService.getOne(id)));
    }


    @GetMapping()
    public List<DeviceDtoShort> getAllWithSizeFromCatalogAndBrand(@RequestParam Map<String, String> param)
            {
                return deviceMapper.mapToDeviceShortDtoList(deviceService.getAll(param));

    }

    /**
     * @param id - unique id of phone
     * @return status of action
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDeviceById(@PathVariable String id) {
        deviceService.delete(id);
        return ResponseEntity.noContent().build();
    }

//    /**
//     * @param deviceRequest - information about phone that was put by user
//     * @return json file with all information about created phone
//     */
//    @PostMapping
//    public ResponseEntity<DeviceDtoFull> createDevice(@RequestBody DeviceRequest deviceRequest) {
//        return ResponseEntity.ok(this.deviceService.create(deviceRequest));
//    }
//
//    /**
//     * @param id - unique id of phone
//     * @param deviceRequest - updated device
//     * @return json file with all information about updated phone
//     */
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateDeviceById(@PathVariable String id, @RequestBody DeviceRequest deviceRequest) {
//        return ResponseEntity.ok(this.deviceService.update(id, deviceRequest));
//    }
}