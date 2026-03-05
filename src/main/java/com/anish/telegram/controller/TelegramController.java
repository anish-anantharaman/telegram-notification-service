package com.anish.telegram.controller;

import com.anish.telegram.dto.ApiResponseDto;
import com.anish.telegram.dto.NotificationRequestDto;
import com.anish.telegram.service.TelegramService;
import com.anish.telegram.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/notifications")
@RequiredArgsConstructor
@Tag(name = "Notifications", description = "APIs for sending Telegram channel notifications")
public class TelegramController {

    private final TelegramService telegramService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Send a Telegram notification",
            description = "Queues a notification to the specific user. " +
                    "Returns 202 if queued successfully, 400 if validation fails, " +
                    "and 500 if the server fails to process the request."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "202",
                    description = "Notification queued successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseDto.class),
                            examples = @ExampleObject(value = Constants.SwaggerDocExamples.SUCCESS)
                    ),
                    headers = {
                            @io.swagger.v3.oas.annotations.headers.Header(
                                    name = "requestId",
                                    description = "Unique ID for tracking this request",
                                    schema = @Schema(type = "string", example = "c828bdd1-2fa6-4d58-8d25-8bb7fa9cdc1f")
                            )
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseDto.class),
                            examples = @ExampleObject(value = Constants.SwaggerDocExamples.BAD_REQUEST)
                    ),
                    headers = {
                            @io.swagger.v3.oas.annotations.headers.Header(
                                    name = "requestId",
                                    description = "Unique ID for tracking this request",
                                    schema = @Schema(type = "string", example = "c828bdd1-2fa6-4d58-8d25-8bb7fa9cdc1f")
                            )
                    }),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseDto.class),
                            examples = @ExampleObject(value = Constants.SwaggerDocExamples.INTERNAL_SERVER_ERROR)
                    ),
                    headers = {
                            @io.swagger.v3.oas.annotations.headers.Header(
                                    name = "requestId",
                                    description = "Unique ID for tracking this request",
                                    schema = @Schema(type = "string", example = "c828bdd1-2fa6-4d58-8d25-8bb7fa9cdc1f")
                            )
                    })
    })
    public ResponseEntity<Object> sendTelegramNotification(@Valid @RequestBody NotificationRequestDto request) {
        telegramService.sendTelegramNotification(request);

        ApiResponseDto response = new ApiResponseDto(
                HttpStatus.ACCEPTED.value(),
                HttpStatus.ACCEPTED.getReasonPhrase(),
                "Notification queued successfully",
                Boolean.TRUE
        );
        return ResponseEntity.accepted().body(response);
    }
}
