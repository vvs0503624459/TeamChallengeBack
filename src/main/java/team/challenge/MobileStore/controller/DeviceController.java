package team.challenge.MobileStore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import team.challenge.MobileStore.dto.mainPage.DeviceDtoShort;
import team.challenge.MobileStore.model.Device;

import java.util.List;
import java.util.Map;

/**
 * Main controller to get all devices, create, update, delete
 */
@RestController
@RequestMapping("/api/v1/devises")
public class DeviceController {

    DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public List<Device> getDevice() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.deviceService.findAll());
    }

    @PostMapping
    public List<Device> addDevice(
            @RequestBody DeviceDto device,
            UriComponentsBuilder uriComponentsBuilder) {
        if (payload.details() == null || payload.details().isBlank()) {
            final var message = this.messageSource
                    .getMessage("tasks.create.details.errors.not_set",
                            new Object[0], locale);
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ErrorsPresentation(
                            List.of(message)));
        } else {
            var device = ;
            this.taskRepository.save(task);
            return ResponseEntity.created(uriComponentsBuilder
                            .path("/api/tasks/{taskId}")
                            .build(Map.of("taskId", task.id())))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(task);
        }
    }

    @PutMapping("/{id}")
    public Device updateDevice(@PathVariable String id, @RequestBody Device updatedDevice) {

    }

    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable String id) {

    }
}