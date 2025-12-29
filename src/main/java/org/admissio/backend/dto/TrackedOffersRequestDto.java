package org.admissio.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.admissio.backend.entity.QuotaType;

import java.util.List;

@Data
@AllArgsConstructor
@Schema(description = "Запит, для відстежуваних пропозицій")
public class TrackedOffersRequestDto {
    @NonNull
    @Valid
    @Schema(description = "Дані для розрахунку конкурсного балу", exampleClasses = ScoreCalculationRequestDto.class, requiredMode = Schema.RequiredMode.REQUIRED)
    private ScoreCalculationRequestDto scoreCalculationRequestDto;

    @NonNull
    @Schema(description = "IDs пропозицій", examples = "[1, 12, 3]", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Long> offerIds;

    @NonNull
    @Schema(description = "Тип квоти", exampleClasses = QuotaType.class, requiredMode = Schema.RequiredMode.REQUIRED)
    private QuotaType quotaType;

    @NonNull
    @Schema(description = "Бюджет", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isBudget;
}
