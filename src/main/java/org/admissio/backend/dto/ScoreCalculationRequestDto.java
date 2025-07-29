package org.admissio.backend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NonNull;

@Data
public class ScoreCalculationRequestDto {

    @NonNull
    @Valid
    private MandatorySubjectsDto mandatorySubjects;

    @NonNull
    @Valid
    private ElectiveSubject electiveSubject;

    @Min(100)
    @Max(200)
    private Integer creativeCompetitionScore;
}
