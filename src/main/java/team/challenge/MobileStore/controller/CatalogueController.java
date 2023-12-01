package team.challenge.MobileStore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.challenge.MobileStore.service.CatalogService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/catalogues")
public class CatalogueController {
    private final CatalogService catalogService;


    @GetMapping
    public ResponseEntity<?> getAllCatalogs() {
        return ResponseEntity.ok(this.catalogService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogResponse> getCatalogById(@PathVariable String id) {
        return ResponseEntity.ok(this.catalogService.getOneById(id));
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<?> deleteCatalogueById(@PathVariable String id) {
        return ResponseEntity.ok(this.catalogService.delete(id));
    }

    @PostMapping
    public ResponseEntity<CatalogueResponse> createCatalogue(@RequestBody CatalogueRequest catalogueRequest) {
        return ResponseEntity.ok(this.catalogService.create(catalogueRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CatalogueResponse> updateCatalogueById(@PathVariable String id, @RequestBody CatalogueRequest catalogueRequest) {
        return ResponseEntity.ok(this.catalogService.update(id, catalogueRequest));
    }
}