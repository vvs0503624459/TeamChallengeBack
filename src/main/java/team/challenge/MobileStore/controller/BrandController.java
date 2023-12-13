package team.challenge.MobileStore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import team.challenge.MobileStore.dto.BrandRequest;
import team.challenge.MobileStore.dto.ReviewResponse;
import team.challenge.MobileStore.exception.ApiError;
import team.challenge.MobileStore.model.Brand;
import team.challenge.MobileStore.service.BrandService;

import java.net.URI;
import java.util.List;

/**
 * Brand controller to get all brands, create, update, delete
 */
@Tag(name = "Brand endpoints", description = "HTTP brand methods.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/brands")
public class BrandController {
    private final BrandService brandService;
    @Operation(summary = "Get brand list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found list of brands",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Brand.class))
                    })
    })
    @GetMapping
    public List<Brand> getAllBrands() {
        return brandService.getAll();
    }
    @Operation(summary = "Get brand by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found brand by id.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Brand.class))
                    }),
            @ApiResponse(responseCode = "404",
            description = "Brand with present ID not found!",
            content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class))
            })
    })
    @GetMapping("/{brand_id}")
    public ResponseEntity<Brand> getOneBrand(@PathVariable(name = "brand_id") String id) {
        return ResponseEntity.ok(brandService.getOneById(id));
    }
    @Operation(summary = "Delete brand by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "404",
                    description = "Brand with present ID not found!",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    @DeleteMapping("/{brand_id}")
    public ResponseEntity<?> deleteBrandById(@PathVariable(name = "brand_id") String id) {
        brandService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Create new Brand with unique title.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "New brand info.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Brand.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Brand with present title is already exist!",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    @PostMapping
    public ResponseEntity<Brand> createBrand(@RequestBody BrandRequest brandRequest) {
        Brand brand = brandService.create(brandRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(brand.getId())
                .toUri();
        return ResponseEntity.created(location).body(brand);
    }

    @Operation(summary = "Update Brand.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Updated brand info.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Brand.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Brand with present title is already exist!",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Brand with present ID not found!",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    @PutMapping("{brand_id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable(name = "brand_id")String brandId, @RequestBody BrandRequest brandRequest) {
        return ResponseEntity.ok(brandService.update(brandId, brandRequest));
    }

}