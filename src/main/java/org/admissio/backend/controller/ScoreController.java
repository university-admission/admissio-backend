package org.admissio.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.admissio.backend.dto.ScoreCalculationRequestDto;
import org.admissio.backend.service.ScoreCalculationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calculate-score/offerId/{id}")
@SecurityRequirements
@Tag(name = "Розрахунок балу", description = "API для розрахунку конкурсного балу вступника")
public class ScoreController {
    private final ScoreCalculationService scoreCalculationService;

    @Operation(
            summary = "Розрахувати конкурсний бал для пропозиції",
            description = "Приймає ID конкурсної пропозиції та бали вступника, повертає розрахований конкурсний бал."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успішний розрахунок",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(type = "number", format = "double", example = "195.34")) }),
            @ApiResponse(responseCode = "400", description = "Некоректні вхідні дані (напр., ID пропозиції не знайдено, бали поза межами 100-200)",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Double> calculateScoreForMajor(
            @Parameter(description = "ID конкурсної пропозиції", required = true, example = "1") @PathVariable("id") Long offerId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Об'єкт із балами вступника з предметів НМТ",
                    required = true
            )
            @RequestBody @Valid ScoreCalculationRequestDto dto) {

        return scoreCalculationService.calculateScore(offerId, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
