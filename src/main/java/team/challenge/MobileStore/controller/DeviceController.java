package team.challenge.MobileStore.controller;


import org.springframework.web.bind.annotation.*;
import team.challenge.MobileStore.model.Device;

import java.util.List;

/**
 * Main controller to get all devices, create, update, delete
 */
@RestController
@RequestMapping("/api/v1/devises")
public class DeviceController {


    @GetMapping
    public List<Device> getDevice() {

    }

    @PostMapping
    public List<Device> addDevice(@RequestBody Device device) {

    }

    @PutMapping("/{id}")
    public Device updateDevice(@PathVariable String id, @RequestBody Device updatedDevice) {

    }

    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable String id) {

    }
}