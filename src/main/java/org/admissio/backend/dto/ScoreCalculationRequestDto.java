package org.admissio.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NonNull;

@Data
@Schema(description = "Дані для розрахунку конкурсного балу")
public class ScoreCalculationRequestDto {

    @NonNull
    @Valid
    @Schema(description = "Бали з обов'язкових предметів")
    private MandatorySubjectsDto mandatorySubjects;

    @NonNull
    @Valid
    @Schema(description = "Бал з вибіркового предмету")
    private ElectiveSubject electiveSubject;

    @Min(100)
    @Max(200)
    @Schema(description = "Бал за творчий конкурс (якщо вимагається спеціальністю)", example = "195", nullable = true)
    private Integer creativeCompetitionScore;
}
