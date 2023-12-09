package team.challenge.MobileStore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import team.challenge.MobileStore.dto.CommentRequest;
import team.challenge.MobileStore.dto.ReviewRequest;
import team.challenge.MobileStore.dto.ReviewResponse;
import team.challenge.MobileStore.exception.ApiError;
import team.challenge.MobileStore.mapper.ReviewMapper;
import team.challenge.MobileStore.model.Likes;
import team.challenge.MobileStore.model.Review;
import team.challenge.MobileStore.service.ReviewService;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
@Tag(name = "Reviews endpoints", description = "HTTP review methods")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;
    @GetMapping
    @Operation(summary = "Get review list of present device.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found list of reviews",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReviewResponse.class))
                    })
    })
    public List<ReviewResponse> getAllDeviceReviews(@RequestParam(required = true) String deviceId){
        return reviewMapper.mapToReviewResponseList(reviewService.getAllByDevice(deviceId));
    }

    @GetMapping("{review_id}")
    @Operation(summary = "Get one Review by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found one review by ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReviewResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Review with present ID not found!",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    public ResponseEntity<ReviewResponse> getOneById(@PathVariable(name = "review_id") String id){
        return ResponseEntity.ok(reviewMapper.mapToReviewResponse(reviewService.getOne(id)));
    }
    @PostMapping
    @Operation(summary = "Create new Review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Create new review",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReviewResponse.class))
                    })
    })
    public ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewRequest reviewRequest){
        Review review = reviewService.create(reviewRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(review.getId())
                .toUri();
        return ResponseEntity.created(location).body(reviewMapper.mapToReviewResponse(review));
    }

    @Operation(summary = "Delete one Review by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Delete one review by ID"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Review with present ID not found!",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    @DeleteMapping("/{review_id}")
    public ResponseEntity<?> deleteReview(@PathVariable(name = "review_id") String id){
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Update one Review by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Update one review by ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReviewResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Review with present ID not found!",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    @PutMapping("/{review_id}")
    public ResponseEntity<ReviewResponse> updateReview(@PathVariable(name = "review_id") String id,
                                                       @RequestBody ReviewRequest reviewRequest){
        return ResponseEntity.ok(reviewMapper.mapToReviewResponse(reviewService.update(id, reviewRequest)));
    }
    @Operation(summary = "Add mark to review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Add like or dislike to review",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReviewResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Review with present ID not found!",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    @PatchMapping("/give-mark/{review_id}")
    public ResponseEntity<ReviewResponse> giveLike(@PathVariable(name = "review_id") String reviewId,
                                                   String userId, Likes mark){
        return ResponseEntity.ok(reviewMapper.mapToReviewResponse(reviewService.giveLike(userId, reviewId, mark)));
    }
    @Operation(summary = "Take mark to review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Take like or dislike to review",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReviewResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Review with present ID not found!",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    @PatchMapping("/take-mark/{review_id}")
    public ResponseEntity<ReviewResponse> takeLike(@PathVariable(name = "review_id") String reviewId, String userId){
        return ResponseEntity.ok(reviewMapper.mapToReviewResponse(reviewService.takeLike(userId, reviewId)));
    }
    @Operation(summary = "Get all tags for device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Set with tags",
                    content = {
                            @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Device with present ID not found!",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    @GetMapping("{device_id}/tags")
    public Set<String> getReviewTags(@PathVariable(name = "device_id") String id){
        return reviewService.getDeviceTags(id);
    }

    @PatchMapping("/{review_id}/reply/{comment_id}")
    public ResponseEntity<ReviewResponse> addAnswer(@PathVariable(name = "review_id") String reviewId,
                                                    @PathVariable(name = "comment_id") String commentId,
                                                    @RequestBody CommentRequest commentRequest){
        return ResponseEntity.ok(reviewMapper.mapToReviewResponse(reviewService.reply(reviewId, commentId, commentRequest)));
    }
}
