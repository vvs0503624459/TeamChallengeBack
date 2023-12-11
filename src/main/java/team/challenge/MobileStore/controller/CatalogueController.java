package team.challenge.MobileStore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.challenge.MobileStore.dto.CatalogueResponse;
import team.challenge.MobileStore.dto.DeviceDtoFull;
import team.challenge.MobileStore.exception.ApiError;
import team.challenge.MobileStore.mapper.CatalogueMapper;
import team.challenge.MobileStore.model.Catalogue;
import team.challenge.MobileStore.service.CatalogueService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/catalogue")
public class CatalogueController {
    private final CatalogueService catalogueService;
    private final CatalogueMapper catalogueMapper;


    @GetMapping
    @Operation(summary = "Get one Catalogue by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found one catalogue by ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CatalogueResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Catalogue with present ID not found!",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    public ResponseEntity<List<CatalogueResponse>> getAllCatalogs() {
        return ResponseEntity.ok(this.catalogueService.getAll().stream().map(catalogueMapper::mapCatalogueToCatalogueResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get all catalogues")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found all catalogues",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = List.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Catalogues weren't found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    public ResponseEntity<?> getCatalogById(
            @PathVariable(name = "id") String catalogueId,
            @RequestParam(name = "nameOfGroup", required = false) Optional<String> catalogueNameGroup
    ) {
        if (catalogueNameGroup.isEmpty()) {
            return ResponseEntity.ok(catalogueMapper.mapCatalogueToCatalogueResponse(this.catalogueService.findById(catalogueId)));
        } else {
            return ResponseEntity.ok(this.catalogueService.getCatalogGroupSpecificationByCatalogueIdAndCatalogueGroupSpecificationName(catalogueId, catalogueNameGroup.get()));
        }
    }

    @PostMapping
    @Operation(summary = "Create one catalogue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Create one catalogue"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Catalogue wasn't created",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    public ResponseEntity<CatalogueResponse> createCatalogue(@RequestBody Catalogue catalogue) {
        return ResponseEntity.ok(catalogueMapper.mapCatalogueToCatalogueResponse(this.catalogueService.create(catalogue)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete one catalogue by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Delete one catalogue by ID"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Catalogue with present ID not found!",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    public ResponseEntity<?> deleteCatalogueById(@PathVariable String id) {
        this.catalogueService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("status", "the catalog with this id was deleted");
        return ResponseEntity.ok(response);
    }

}