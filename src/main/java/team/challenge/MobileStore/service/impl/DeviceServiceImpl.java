package team.challenge.MobileStore.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import team.challenge.MobileStore.dto.DeviceRequest;
import team.challenge.MobileStore.exception.ModelNotFoundException;
import team.challenge.MobileStore.model.*;
import team.challenge.MobileStore.repositories.CatalogRepository;
import team.challenge.MobileStore.repositories.DeviceCriteriaRepository;
import team.challenge.MobileStore.repositories.DeviceRepository;
import team.challenge.MobileStore.service.CatalogService;
import team.challenge.MobileStore.service.DeviceService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private static final String CATALOGUE = "catalogue";
    private static final String BRAND = "brand";
    private static final String PRICE = "price";
    private static final String DIAGONAL = "screen diagonal";
    private static final String SPECIFICATION = "specificationGroups.specifications";
    private final DeviceRepository deviceRepository;
    private final DeviceCriteriaRepository deviceCriteriaRepository;
    @Override
    public List<Device> getAll(Map<String, String> params) {
        if (params.isEmpty() ) {
            return deviceRepository.findAll();
        }
        List<Criteria> criteriaList = new ArrayList<>();
        for (Map.Entry<String, String> entry: params.entrySet()){
            switch (entry.getKey()){
                case "catalogue" -> criteriaList.add(createCriteriaIs(CATALOGUE, entry.getValue()));
                case "brand" -> {
                    List<String> paramList = List.of(entry.getValue().split(","));
                    criteriaList.add(createCriteriaIn(BRAND, paramList));
                }
                case "price" -> {
                    List<String> prices = List.of(entry.getValue().split("-"));
                    criteriaList.add(createCriteriaBetween(PRICE, prices.get(0), prices.get(1)));
                }
                case "screen diagonal" -> {

                    List<String> diagonals = List.of(entry.getValue().split(","));
                    Set<Double> diagonalsSet = new HashSet<>();
                    for (String diagonalString :
                            diagonals) {
                        String[] diagonalsToParse = diagonalString.split("-");
                        diagonalsSet.add(Double.parseDouble(diagonalsToParse[0].replaceAll("'","")));
                        diagonalsSet.add(Double.parseDouble(diagonalsToParse[1].replaceAll("'","")));
                    }
                    String diagonalMin = String.valueOf(Collections.min(diagonalsSet));
                    String diagonalMax = String.valueOf(Collections.max(diagonalsSet));
                    criteriaList.add(Criteria.where(SPECIFICATION).elemMatch(createCriteriaBetween(DIAGONAL, diagonalMin, diagonalMax)));

                }
                default -> {
                    List<String> paramList = List.of(entry.getValue().split(","));
                    if (paramList.size() == 1){
                        criteriaList.add(Criteria.where(SPECIFICATION).elemMatch(createCriteriaIs("value", entry.getValue())));
                    } else {
                        criteriaList.add(Criteria.where(SPECIFICATION).elemMatch(createCriteriaIn("value", paramList)));
                    }
                }
            }
        }
        return deviceCriteriaRepository.findAll(new Query().addCriteria(new Criteria().andOperator(criteriaList)));
//        if (params.containsKey("catalogue")) {
//            criteriaList.add(Criteria.where("catalogue").is(params.get("catalogueId")));
//        } else if (params.containsKey("brand")) {
//            criteriaList.add(Criteria.where("brand").is(params.get("catalogueId")));
//        } else if (params.containsKey("price")) {
//            String[] prices = params.get("price").split("-");
//            criteriaList.add(Criteria.where("price").gte(prices[0]).lte(prices[1]));
//        } else {
//            params.forEach((key, val) -> {
//                if (key.equals("catalogueId") || key.equals("brandId") || key.equals("price")){
//                    return;
//                }
//                List<String> sameParams = List.of(val.split(","));
//                if (sameParams.size() == 1){
//                    criteriaList.add(Criteria.where("specificationGroups.specifications").is(val));
//                } else {
//                    criteriaList.add(Criteria.where("specificationGroups.specifications").in(sameParams));
//                }
//            });
//        }
    }


    private Criteria createCriteriaIs(String key, String param){
        return Criteria.where(key).is(param);
    }
    private Criteria createCriteriaIn(String key, List<String> params){
        return Criteria.where(key).in(params);
    }
    private Criteria createCriteriaBetween(String key, String firstValue, String secondValue){
        return Criteria.where(key).gte(firstValue).lte(secondValue);
    }

    @Override
    public List<Device> getAllFromCatalogueAndBrand(@NonNull Catalogue catalogue, @NonNull Brand brand) {
        return deviceRepository.findAllByCatalogueAndBrand(catalogue, brand);
    }

    @Override
    public List<Device> getAllWithSameColor(@NonNull String deviceId, @NonNull String color) {
        String deviceSeries = getDeviceSpecification(getOne(deviceId)).stream().filter(specification -> specification.getTitle().equals("series")).map(Specification::getValue).collect(Collectors.joining());
        return deviceRepository.getAllBySeriesAndColor(deviceSeries, color);
    }

    @Override
    public List<Device> getAllWithSameMemory(@NonNull String deviceId, @NonNull String internalMemory) {
        String deviceSeries = getDeviceSpecification(getOne(deviceId)).stream().filter(specification -> specification.getTitle().equals("series")).map(Specification::getValue).collect(Collectors.joining());
        return deviceRepository.getAllBySeriesAndInternalMemory(deviceSeries, internalMemory);
    }

    @Override
    public Device getOne(@NonNull String deviceId) {
        return deviceRepository.findById(deviceId).orElseThrow(()-> new ModelNotFoundException(String.format("Device with id: %s not found", deviceId)));
    }

    @Override
    public Device create(@NonNull DeviceRequest deviceRequest) {
        return null;
    }

    @Override
    public Device update(@NonNull String deviceId, @NonNull DeviceRequest deviceRequest) {
        return null;
    }

    @Override
    public void delete(@NonNull String deviceId) {
        deviceRepository.delete(getOne(deviceId));
    }

    private List<Specification> getDeviceSpecification(@NonNull Device device){
        List<Specification> res = new ArrayList<>();
        for (SpecificationGroup group :
                device.getSpecificationGroups()) {
            res.addAll(group.getSpecifications());
        }
        return res;
    }

}
