package team.challenge.MobileStore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.challenge.MobileStore.model.Catalogue;
import team.challenge.MobileStore.model.CatalogueGroupSpecification;
import team.challenge.MobileStore.service.CatalogueService;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/catalogue")
public class CatalogueController {
    private final CatalogueService catalogueService;


    @GetMapping
    public ResponseEntity<List<Catalogue>> getAllCatalogs() {
        return ResponseEntity.ok(this.catalogueService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCatalogById(
            @PathVariable(name = "id") String catalogueId,
            @RequestParam(name = "nameOfGroup",required = false) Optional<String> catalogueNameGroup
    ) {
        if (catalogueNameGroup.isEmpty()) {
            return ResponseEntity.ok(this.catalogueService.findById(catalogueId));
        } else {
            return ResponseEntity.ok(this.catalogueService.getCatalogGroupSpecificationByCatalogueIdAndCatalogueGroupSpecificationName(catalogueId, catalogueNameGroup.get()));
        }
    }

    @PostMapping
    public ResponseEntity<Catalogue> createCatalogue(@RequestBody Catalogue catalogue) {
        return ResponseEntity.ok(this.catalogueService.create(catalogue));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCatalogueById(@PathVariable String id) {
        this.catalogueService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("status", "the catalog with this id was deleted");
        return ResponseEntity.ok(response);
    }

    /*@PatchMapping("/{id}")
    public ResponseEntity<CatalogueResponse> updateCatalogueById(@PathVariable String id, @RequestBody CatalogueRequest catalogueRequest) {
        return ResponseEntity.ok(this.catalogueService.update(id, catalogueRequest));
    }*/
}