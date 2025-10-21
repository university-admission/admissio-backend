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
import org.admissio.backend.entity.Major;
import org.admissio.backend.service.MajorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/majors")
@RequiredArgsConstructor
@SecurityRequirements
@Tag(name = "Спеціальності", description = "API для отримання інформації про спеціальності та їх коефіцієнти")
public class MajorController {
    private final MajorService majorService;

    @Operation(
            summary = "Отримати список усіх спеціальностей",
            description = "Повертає повний список спеціальностей з усіма ваговими коефіцієнтами для розрахунку конкурсного балу."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успішне отримання списку",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Major.class))) })
    })
    @GetMapping
    public ResponseEntity<List<Major>> getAllMajors() {
        return new ResponseEntity<>(majorService.findAll(), HttpStatus.OK);
    }
}
