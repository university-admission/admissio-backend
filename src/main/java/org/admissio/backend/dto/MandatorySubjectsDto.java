package org.admissio.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NonNull;

@Data
public class MandatorySubjectsDto {
    @NonNull
    @Min(100)
    @Max(200)
    private Integer ukrainian;

    @NonNull
    @Min(100)
    @Max(200)
    private Integer math;

    @NonNull
    @Min(100)
    @Max(200)
    private Integer history;
}
