package com.ercarts.clock.controller;

import java.net.URI;
import java.util.List;

import com.ercarts.clock.domain.Clock;
import com.ercarts.clock.error.ErrorResponse;
import com.ercarts.clock.model.ClockRegistration;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

/**
 * @author dkyryk
 */
@Tag(name = "Clock API")
public interface ClockControllerDocumentation {

    @Operation(summary = "Get all registered clocks")
    List<Clock> allClocks();

    @Operation(summary = "Get registered clock by id")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Found clock"),
                    @ApiResponse(responseCode = "404", description = "Clock not found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    Clock clockById( Long id);

    @Operation(summary = "Register additional clock")
    ResponseEntity<Clock> registerClock(ClockRegistration clockRegistration);
}