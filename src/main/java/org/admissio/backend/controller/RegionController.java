package org.admissio.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.admissio.backend.entity.UniversityRegion;
import org.admissio.backend.service.UniversityRegionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
@SecurityRequirements
@Tag(name = "Регіони", description = "API для отримання інформації про регіони (області)")
public class RegionController {
    private final UniversityRegionService universityRegionService;

    @Operation(
            summary = "Отримати список усіх регіонів",
            description = "Повертає повний список регіонів, доступних для фільтрації університетів."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успішне отримання списку",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UniversityRegion.class))) })
    })
    @GetMapping
    public ResponseEntity<List<UniversityRegion>> getAllRegions() {
        return new ResponseEntity<>(universityRegionService.findAll(), HttpStatus.OK);
    }
}
