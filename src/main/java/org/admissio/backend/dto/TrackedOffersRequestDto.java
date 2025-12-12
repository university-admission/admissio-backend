package org.admissio.backend.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.admissio.backend.entity.QuotaType;

import java.util.List;

@Data
@AllArgsConstructor
public class TrackedOffersRequestDto {
    @NonNull
    @Valid
    private ScoreCalculationRequestDto scoreCalculationRequestDto;

    @NonNull
    private List<Long> offerIds;

    @NonNull
    private QuotaType quotaType;

    @NonNull
    private Boolean isBudget;
}
