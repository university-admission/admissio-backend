package org.admissio.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.admissio.backend.entity.QuotaType;

@Data
@AllArgsConstructor
@Schema(description = "Відповідь, для заяв студента")
public class StudentApplicationDto {
    @NonNull
    @Schema(description = "Пріорітет студента", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer priority;

    @NonNull
    @Schema(description = "Чи актуальна заява", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isActual;

    @NonNull
    @Schema(description = "Чи проходить заявка", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isCounted;

    @NonNull
    @Schema(description = "Конкурсний бал студента", example = "179", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double score;

    @NonNull
    @Schema(description = "Тип квоти", exampleClasses = QuotaType.class, requiredMode = Schema.RequiredMode.REQUIRED)
    private QuotaType quotaType;

    @NonNull
    @Schema(description = "Чи бюджетна заява", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isBudget;

    @NonNull
    @Schema(description = "Назва університету", example = "КПІ", requiredMode = Schema.RequiredMode.REQUIRED)
    private String universityName;

    @NonNull
    @Schema(description = "Назва факультету", example = "Фі", requiredMode = Schema.RequiredMode.REQUIRED)
    private String facultyName;

    @NonNull
    @Schema(description = "Назва спеціальності", example = "F1, Інженерія програмного забезпечення", requiredMode = Schema.RequiredMode.REQUIRED)
    private String majorName;
}
