package org.admissio.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.admissio.backend.entity.University;
import org.admissio.backend.service.UniversityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universities")
@RequiredArgsConstructor
@Tag(name = "Університети", description = "API для отримання інформації про університети")
@SecurityRequirements
public class UniversityController {
    private final UniversityService universityService;

    @Operation(
            summary = "Отримати список усіх університетів",
            description = "Повертає повний список університетів. " +
                    "Якщо вказано необов'язковий параметр 'regionName', " +
                    "список буде відфільтровано за відповідним регіоном."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успішне отримання списку",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = University.class))) })
    })
    @GetMapping
    public List<University> getUniversities(
            @Parameter(
                    name = "regionName",
                    description = "Назва міста (регіону) для фільтрації",
                    example = "Київ"
            )
            @RequestParam(required = false) String regionName) {
        if (regionName != null && !regionName.isBlank()) {
            return universityService.findAllByRegion(regionName);
        }
        return universityService.findAll();
    }
}
