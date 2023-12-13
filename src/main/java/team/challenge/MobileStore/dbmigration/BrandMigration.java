package team.challenge.MobileStore.dbmigration;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import team.challenge.MobileStore.model.Brand;
import team.challenge.MobileStore.repositories.BrandRepository;
import team.challenge.MobileStore.service.BrandService;

import java.util.List;

@ChangeLog(order = "001")
public class BrandMigration {
    @ChangeSet(order = "001", id = "init_first_brands", author = "Romych")
    public void initBrands(BrandRepository brandRepository){
        List<Brand> brands = List.of(
                new Brand("Apple"),
                new Brand("Samsung"),
                new Brand("Xiaomi"),
                new Brand("Nokia"),
                new Brand("OnePlus"),
                new Brand("ZTE"),
                new Brand("OPPO"),
                new Brand("Google Pixel"),
                new Brand("Realme"),
                new Brand("Motorola")
        );
        brandRepository.saveAll(brands);
    }
}
