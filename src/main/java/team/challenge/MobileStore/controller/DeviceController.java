package team.challenge.MobileStore.controller;


import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.challenge.MobileStore.model.Device;

import java.util.List;

@RestController
@RequestMapping("/api/v1/devises")
public class DeviceController {


    @GetMapping
    public List<Device> getDeviceForMainPage() {

    }
}