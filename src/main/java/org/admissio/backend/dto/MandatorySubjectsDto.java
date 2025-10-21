package org.admissio.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NonNull;

@Data
@Schema(description = "Бали з обов'язкових предметів НМТ/ЗНО")
public class MandatorySubjectsDto {
    @NonNull
    @Min(100)
    @Max(200)
    @Schema(description = "Бал з української мови", example = "180", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer ukrainian;

    @NonNull
    @Min(100)
    @Max(200)
    @Schema(description = "Бал з математики", example = "195", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer math;

    @NonNull
    @Min(100)
    @Max(200)
    @Schema(description = "Бал з історії України", example = "188", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer history;
}
