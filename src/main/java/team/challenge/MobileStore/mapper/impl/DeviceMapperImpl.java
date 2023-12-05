package team.challenge.MobileStore.mapper.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.challenge.MobileStore.dto.DeviceDtoFull;
import team.challenge.MobileStore.dto.DeviceDtoShort;
import team.challenge.MobileStore.dto.SameDeviceDto;
import team.challenge.MobileStore.mapper.DeviceMapper;
import team.challenge.MobileStore.mapper.ReviewMapper;
import team.challenge.MobileStore.model.Device;
import team.challenge.MobileStore.model.Review;
import team.challenge.MobileStore.model.Specification;
import team.challenge.MobileStore.model.SpecificationGroup;
import team.challenge.MobileStore.service.DeviceService;
import team.challenge.MobileStore.service.ReviewService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DeviceMapperImpl implements DeviceMapper {
    private final String COLOR = "color";
    private final String MEMORY = "internal memory";
    private final DeviceService deviceService;
    private final ReviewMapper reviewMapper;
    private final ReviewService reviewService;
    @Override
    public DeviceDtoFull mapToFullDto(@NonNull Device device) {
        String title = getTitle(device);
        List<Device> deviceServiceAllWithSameColor= deviceService.getAllWithSameColor(device.getId(), findInSpecification(device, COLOR));
        List<Device> devicesWithSameMemory = deviceService.getAllWithSameMemory(device.getId(), findInSpecification(device, MEMORY));
        return new DeviceDtoFull(device.getId(),
                title,
                device.getPrice(),
                mapToSameDeviceDtoList(deviceServiceAllWithSameColor),
                mapToSameDeviceDtoList(devicesWithSameMemory),
                device.getUriPhotos(),
                device.getPresentations(),
                device.getSpecificationGroups(),
                null,
                null);
    }

    private String getTitle(Device device) {
        String color = findInSpecification(device, COLOR);
        String series = findInSpecification(device, "series");
        String memory = findInSpecification(device, MEMORY);
        return String.format("%s %s %s %s %s", device.getBrand().getTitle(), series, memory, color, device.getSkuCode());
    }

    @Override
    public DeviceDtoShort mapToShortDto(@NonNull Device device) {

        // brand + series + internal memory + color + skuCode

        String title = getTitle(device);
        String color = findInSpecification(device, COLOR);
        List<Device> deviceServiceAllWithSameColor= deviceService.getAllWithSameColor(device.getId(), color);
        List<Review> reviews = reviewService.getAllByDevice(device.getId());
        return new DeviceDtoShort(device.getId(), title, color, device.getUriMainPhoto(), reviewMapper.mapToReviewMarkDto(reviews), device.getPrice(), device.getDiscount(), mapToDeviceWithSameColorDtoList(deviceServiceAllWithSameColor));
    }

    @Override
    public List<SameDeviceDto> mapToSameDeviceDtoList(List<Device> devices) {
        List<SameDeviceDto> sameDevices = new ArrayList<>();
        for (Device device :
                devices) {
            sameDevices.add(mapToSameDeviceDto(device));
        }
        return sameDevices;
    }

    @Override
    public SameDeviceDto mapToSameDeviceDto(Device device) {
        return new SameDeviceDto(device.getId(), findInSpecification(device, COLOR));
    }

    private String findInSpecification(Device device, String specificationTitle){
        List<Specification> specifications = new ArrayList<>();
        for (SpecificationGroup group :
                device.getSpecificationGroups()) {
            specifications.addAll(group.getSpecifications());
        }
        return specifications.stream().filter(specification -> specification.getTitle().equals(specificationTitle)).map(Specification::getValue).collect(Collectors.joining());
    }



}
