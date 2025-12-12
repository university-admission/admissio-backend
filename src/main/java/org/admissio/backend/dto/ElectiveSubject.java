package org.admissio.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Вибірковий предмет та бал з нього")
public class ElectiveSubject {

    @NotNull
    @Schema(description = "Назва вибіркового предмету (англійською)", example = "foreign language", requiredMode = Schema.RequiredMode.REQUIRED)
    private String subjectName;

    @NotNull
    @Min(100)
    @Max(200)
    @Schema(description = "Бал з вибіркового предмету", example = "192", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer score;
}

