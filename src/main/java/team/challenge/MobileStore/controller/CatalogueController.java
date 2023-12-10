package team.challenge.MobileStore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.challenge.MobileStore.model.Catalogue;
import team.challenge.MobileStore.service.CatalogueService;

import java.util.List;

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
    public ResponseEntity<Catalogue> getCatalogById(@PathVariable(name = "id") String catalogueId) {
        return ResponseEntity.ok(this.catalogueService.findById(catalogueId));
    }

    /*@DeleteMapping("{/id}")
    public ResponseEntity<?> deleteCatalogueById(@PathVariable String id) {
        return ResponseEntity.ok(this.catalogueService.delete(id));
    }

    @PostMapping
    public ResponseEntity<CatalogueResponse> createCatalogue(@RequestBody CatalogueRequest catalogueRequest) {
        return ResponseEntity.ok(this.catalogueService.create(catalogueRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CatalogueResponse> updateCatalogueById(@PathVariable String id, @RequestBody CatalogueRequest catalogueRequest) {
        return ResponseEntity.ok(this.catalogueService.update(id, catalogueRequest));
    }*/
}