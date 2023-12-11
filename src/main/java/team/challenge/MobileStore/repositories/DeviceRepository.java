package team.challenge.MobileStore.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import team.challenge.MobileStore.model.*;

import java.util.List;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {
    @Query("{$and: [{'specificationGroups.specifications': {$elemMath:  {'title': 'series', 'value': ?0}}}, {'specificationGroups.specifications': {$elemMath:  {'title': 'internalMemory', 'value': ?1}}}]}")
    List<Device> getAllBySeriesAndInternalMemory(String series, String internalMemory);
    @Query("{$and: [{'specificationGroups.specifications': {$elemMath:  {'title': 'series', 'value': ?0}}}, {'specificationGroups.specifications': {$elemMath:  {'title': 'color', 'value': ?1}}}]}")
    List<Device> getAllBySeriesAndColor(String series, String color);
    @Query("{'specificationGroups.specifications': {$elemMath:  {'title': 'series', 'value': ?0}}}")
    List<Device> getAllBySeries(String series);

    List<Device> findAllByCatalogueAndBrand(Catalogue catalog, Brand brand);
    List<Device> findAllByReviews(List<Review> review);
    List<Device> findAllByQuestions(List<Question> questions);

}