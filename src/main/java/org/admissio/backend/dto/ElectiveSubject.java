package org.admissio.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ElectiveSubject {

    @NotNull
    private String subjectName;

    @NotNull
    @Min(100)
    @Max(200)
    private Integer score;
}

