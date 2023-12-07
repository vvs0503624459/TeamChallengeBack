package team.challenge.MobileStore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Brand controller to get all brands, create, update, delete
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/brands")
public class BrandController {
//    private final BrandService brandService;
//
//    @GetMapping
//    public ResponseEntity<List<?>> getAllBrands() {
//        return ResponseEntity.ok(this.brandService.getAll());
//    }
//
//    @GetMapping
//    public ResponseEntity<List<?>> getAllByCatalog(@RequestBody Catalogue catalogue) {
//        return ResponseEntity.ok(this.brandService.getAllByCatalog(catalogue));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<BrandResponce> getOneBrand(@PathVariable String id) {
//        return ResponseEntity.ok(this.brandService.getOne(id));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteBrandById(@PathVariable String id) {
//        return ResponseEntity.ok(this.brandService.delete(id));
//    }
//
//
//    @PostMapping
//    public ResponseEntity<BrandResponce> createBrand(@RequestBody BrandRequest brandRequest) {
//        return ResponseEntity.ok(this.brandService.create(brandRequest));
//    }
//
//    @PatchMapping("{/id}")
//    public ResponseEntity<BrandResponce> updateBrand(String brandId, @RequestBody BrandRequest brandRequest) {
//        return ResponseEntity.ok(brandService.update(brandId, brandRequest));
//    }

}