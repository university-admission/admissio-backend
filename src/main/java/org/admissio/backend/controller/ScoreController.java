package org.admissio.backend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
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
public class ScoreController {

    private final ScoreCalculationService scoreCalculationService;

    @PostMapping
    public ResponseEntity<Double> calculateScoreForMajor(
            @PathVariable("id") Long offerId,
            @RequestBody @Valid ScoreCalculationRequestDto dto) {

        return scoreCalculationService.calculateScore(offerId, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
