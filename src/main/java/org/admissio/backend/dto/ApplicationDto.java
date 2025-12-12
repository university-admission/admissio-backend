package org.admissio.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Відповідь для заяви по пропозиції")
public class ApplicationDto {
    @NotNull
    @Schema(description = "ID студента", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long studentId;

    @NotNull
    @Schema(description = "Ім'я студента", example = "Петренко І. І.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String studentName;

    @NotNull
    @Schema(description = "Конкурсний бал студента", example = "179", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double score;

    @NotNull
    @Schema(description = "Пріорітет студента", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer priority;

    @NotNull
    @Schema(description = "Чи актуальна ця заява", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isActual;

    @NotNull
    @Schema(description = "Чи враховується ця заява в конкурсі", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isCounted;
}
