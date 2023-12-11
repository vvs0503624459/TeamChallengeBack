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
import team.challenge.MobileStore.dto.CommentRequest;
import team.challenge.MobileStore.dto.QuestionRequest;
import team.challenge.MobileStore.dto.QuestionResponse;
import team.challenge.MobileStore.dto.ReviewResponse;
import team.challenge.MobileStore.exception.ApiError;
import team.challenge.MobileStore.mapper.QuestionMapper;
import team.challenge.MobileStore.model.Question;
import team.challenge.MobileStore.service.QuestionService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/question")
@Tag(name = "Question endpoints", description = "HTTP question methods")
public class QuestionController {
    private final QuestionMapper questionMapper;
    private final QuestionService questionService;
    @Operation(summary = "Get question list of present device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Found list of question.",
            content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = QuestionResponse.class))
            }),
            @ApiResponse(responseCode = "404",
            description = "Device with present ID not found!",
            content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponse.class))
            })
    })
    @GetMapping
    public List<QuestionResponse> getAllDeviceQuestion(@RequestParam String deviceId){
        return questionMapper.mapToResponseList(questionService.getAllByDevice(deviceId));
    }
    @Operation(summary = "Get question by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found one question.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = QuestionResponse.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Question with present ID not found!",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class))
                    })
    })
    @GetMapping("{question_id}")
    public ResponseEntity<QuestionResponse> getOne(@PathVariable(name = "question_id") String questionId){
        return ResponseEntity.ok(questionMapper.mapToResponse(questionService.getOne(questionId)));
    }
    @Operation(summary = "Create new Question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Create new question",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReviewResponse.class))
                    })
    })
    @PostMapping
    public ResponseEntity<QuestionResponse> create(@RequestBody QuestionRequest questionRequest){
        Question question = questionService.create(questionRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(question.getId())
                .toUri();
        return ResponseEntity.created(location).body(questionMapper.mapToResponse(question));
    }
    @Operation(summary = "Delete one question by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Delete one question by ID"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Question with present ID not found!",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    @DeleteMapping("{question_id}")
    public ResponseEntity<?> delete(@PathVariable(name = "question_id") String id){
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Update one question by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Update one question by ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReviewResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Question with present ID not found!",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    @PutMapping("{question_id}")
    public ResponseEntity<QuestionResponse> update(@PathVariable(name = "question_id") String questionId,
                                                   @RequestBody QuestionRequest questionRequest){
        return ResponseEntity.ok(questionMapper.mapToResponse(questionService.update(questionId, questionRequest)));
    }
    @Operation(summary = "Reply comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Add answer to comment",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReviewResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Question with present ID not found! \n" +
                            "Comment with present ID not found! ",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    @PatchMapping("/{question_id}/reply/{comment_id}")
    public ResponseEntity<QuestionResponse> reply(@PathVariable(name = "question_id") String questionId,
                                                  @PathVariable(name = "comment_id") String commentId,
                                                  @RequestBody CommentRequest commentRequest){
        return ResponseEntity.ok(questionMapper.mapToResponse(questionService.reply(questionId, commentId, commentRequest)));
    }
}
