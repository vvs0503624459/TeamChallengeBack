package team.challenge.MobileStore.mapper.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.challenge.MobileStore.dto.DeviceDtoFull;
import team.challenge.MobileStore.dto.DeviceDtoShort;
import team.challenge.MobileStore.dto.DeviceWithSameColorDto;
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
    private final String SERIES = "series";
    private final String MEMORY = "internal memory";
    private final String COLOR = "color";
    private final DeviceService deviceService;
    private final ReviewMapper reviewMapper;
    private final ReviewService reviewService;
    @Override
    public DeviceDtoFull mapToFullDto(@NonNull Device device) {

        return null;
    }

    @Override
    public DeviceDtoShort mapToShortDto(@NonNull Device device) {

        // brand + series + internal memory + color + skuCode
        String color = findInSpecification(device, COLOR);
        String series = findInSpecification(device, SERIES);
        String memory = findInSpecification(device, MEMORY);
        String title = String.format("%s %s %s %s %s", device.getBrand().getTitle(), series, memory, color, device.getSkuCode());
        List<Device> deviceServiceAllWithSameColor= deviceService.getAllWithSameColor(device.getId(), color);
        List<Review> reviews = reviewService.getAllByDevice(device.getId());
        DeviceDtoShort dtoShort = new DeviceDtoShort(device.getId(), title, color, device.getUriMainPhoto(), reviewMapper.mapToReviewMarkDto(reviews), device.getPrice(), device.getDiscount(), mapToDeviceWithSameColorDtoList(deviceServiceAllWithSameColor));
        return null;
    }

    @Override
    public List<DeviceWithSameColorDto> mapToDeviceWithSameColorDtoList(List<Device> devices) {
        List<DeviceWithSameColorDto> sameDevices = new ArrayList<>();
        for (Device device :
                devices) {
            sameDevices.add(mapToDeviceWithSameColorDto(device));
        }
        return sameDevices;
    }

    @Override
    public DeviceWithSameColorDto mapToDeviceWithSameColorDto(Device device) {
        return new DeviceWithSameColorDto(device.getId(), findInSpecification(device, COLOR));
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
