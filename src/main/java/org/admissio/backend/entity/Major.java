package org.admissio.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import java.util.Set;
import java.util.stream.Stream;

@Entity
@Table(name = "majors")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Спеціальність та її вагові коефіцієнти для розрахунку конкурсного балу")
public class Major {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Schema(description = "Унікальний ідентифікатор спеціальності", example = "1")
    private Long id;

    @NonNull
    @Column(name = "major_name", nullable = false)
    @Schema(description = "Назва спеціальності", example = "Комп'ютерні науки")
    private String majorName;

    @NonNull
    @Column(name = "major_code", nullable = false, unique = true)
    @Schema(description = "Код спеціальності", example = "F1")
    private String majorCode;

    @NonNull
    @Column(name = "uk_lang_coef", nullable = false)
    @Min(0)
    @Max(1)
    @Schema(description = "Коефіцієнт для предмету 'Українська мова'", example = "0.3")
    private Double ukLanguageCoef;

    @NonNull
    @Column(name = "math_coef", nullable = false)
    @Min(0)
    @Max(1)
    @Schema(description = "Коефіцієнт для предмету 'Математика'", example = "0.5")
    private Double mathCoef;

    @NonNull
    @Column(name = "history_coef", nullable = false)
    @Min(0)
    @Max(1)
    @Schema(description = "Коефіцієнт для предмету 'Історія України'", example = "0.2")
    private Double historyCoef;

    @NonNull
    @Column(name = "uk_literature_coef", nullable = false)
    @Min(0)
    @Max(1)
    @Schema(description = "Коефіцієнт для предмету 'Українська література'", example = "0.2")
    private Double ukLiteratureCoef;

    @NonNull
    @Column(name = "foreign_lang_coef", nullable = false)
    @Min(0)
    @Max(1)
    @Schema(description = "Коефіцієнт для предмету 'Іноземна мова'", example = "0.3")
    private Double foreignLangCoef;

    @NonNull
    @Column(name = "biology_coef", nullable = false)
    @Min(0)
    @Max(1)
    @Schema(description = "Коефіцієнт для предмету 'Біологія'", example = "0.2")
    private Double biologyCoef;

    @NonNull
    @Column(name = "geography_coef", nullable = false)
    @Min(0)
    @Max(1)
    @Schema(description = "Коефіцієнт для предмету 'Географія'", example = "0.2")
    private Double geographyCoef;

    @NonNull
    @Column(name = "physics_coef", nullable = false)
    @Min(0)
    @Max(1)
    @Schema(description = "Коефіцієнт для предмету 'Фізика'", example = "0.2")
    private Double physicsCoef;

    @NonNull
    @Column(name = "chemistry_coef", nullable = false)
    @Min(0)
    @Max(1)
    @Schema(description = "Коефіцієнт для предмету 'Хімія'", example = "0.2")
    private Double chemistryCoef;

    @NonNull
    @Column(name = "competition_coef", nullable = false)
    @Min(0)
    @Max(1)
    @Schema(description = "Регіональний коефіцієнт", example = "1.04")
    private Double competitionCoef;

    @NonNull
    @Column(name = "major_coef", nullable = false)
    @Max(2)
    @Min(1)
    @Schema(description = "Галузевий коефіцієнт", example = "1.02")
    private Double majorCoef;

    @JsonIgnore
    @OneToMany(mappedBy = "major")
    private Set<Offer> offers;

    @JsonIgnore
    public double getMaxElectiveCoef() {
        return Stream.of(
                ukLiteratureCoef,
                foreignLangCoef,
                biologyCoef,
                geographyCoef,
                physicsCoef,
                chemistryCoef
        ).max(Double::compare).orElse(0.0);
    }

    @JsonIgnore
    public Double getCoefForElectiveSubjectByName(String electiveSubject) {
        if (electiveSubject == null || electiveSubject.isBlank()) {
            return null;
        }

        return switch (electiveSubject.toLowerCase()) {
            case "ukrainian literature" -> ukLiteratureCoef;
            case "foreign language" ->foreignLangCoef;
            case "biology" -> biologyCoef;
            case "geography" -> geographyCoef;
            case "physics" -> physicsCoef;
            case "chemistry" -> chemistryCoef;
            default -> null;
        };
    }
}
