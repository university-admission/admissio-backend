package org.admissio.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.admissio.backend.entity.EducationForm;

@Data
@AllArgsConstructor
@Schema(description = "Відповідь для конкретної відстежуваної пропозиції")
public class TrackedOfferAnalyticsDto {
    @NotNull
    @Schema(description = "ID пропозиції", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long offerId;

    @NotNull
    @Schema(description = "Назва університету", example = "КПІ", requiredMode = Schema.RequiredMode.REQUIRED)
    private String universityName;

    @NotNull
    @Schema(description = "Назва спеціальності", example = "ІПЗ", requiredMode = Schema.RequiredMode.REQUIRED)
    private String majorName;

    @NotNull
    @Schema(description = "Назва пропозиції", example = "Прикладана математика", requiredMode = Schema.RequiredMode.REQUIRED)
    private String offerName;

    @NotNull
    @Schema(description = "Назва факультету", example = "ФІ", requiredMode = Schema.RequiredMode.REQUIRED)
    private String facultyName;

    @NotNull
    @Schema(description = "Форма навчання", exampleClasses = EducationForm.class, requiredMode = Schema.RequiredMode.REQUIRED)
    private EducationForm educationForm;

    @Schema(description = "Оцінка користувача", example = "172.33", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Double userScore;

    @Schema(description = "Місце користувача, серед актуальних пропозицій", example = "12", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer rankActual;

    @Schema(description = "Місце користувача, серед усіх пропозицій", example = "21", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer rankAll;

    @NotNull
    @Schema(description = "Кількість місць", example = "123", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer places;
}
