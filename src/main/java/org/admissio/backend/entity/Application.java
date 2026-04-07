package org.admissio.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name = "applications")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Інформація про заяву вступника на конкурсну пропозицію")
public class Application {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Schema(description = "Унікальний ідентифікатор заяви", example = "1")
    private Long id;

    @JsonIgnore
    @NonNull
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    private Student student;

    @JsonIgnore
    @NonNull
    @ManyToOne
    @JoinColumn(name = "offer_id", referencedColumnName = "id", nullable = false)
    private Offer offer;

    @Column(name = "score", nullable = false)
    @NonNull
    @Min(0)
    @Max(200)
    @Schema(description = "Конкурсний бал вступника", example = "191.5")
    private Double score;

    @Column(name = "priority", nullable = false)
    @NonNull
    @Min(1)
    @Max(15)
    @Schema(description = "Пріоритет заяви (від 1 до 15)", example = "1")
    private Integer priority;

    @Column(name = "is_budget", nullable = false)
    @NonNull
    @Schema(description = "Чи претендує заява на бюджетну форму навчання", example = "true")
    private Boolean isBudget;

    @Column(name = "quota_type", nullable = false)
    @NonNull
    @Enumerated(EnumType.STRING)
    @Schema(description = "Тип квоти, за якою подано заяву", example = "GENERAL")
    private QuotaType quotaType;

    @Column(name = "is_actual", nullable = false)
    @Schema(description = "Чи є заява актуальною (чи подав студент заяви з вищим пріорітетом)", example = "true")
    private Boolean isActual = false;

    @Column(name = "is_counted", nullable = false)
    @Schema(description = "Чи врахована заява в конкурсному відборі, тобто чи не проходить студент по іншій заяві", example = "false")
    private Boolean isCounted = false;

    @Column(name = "is_checked", nullable = false)
    @JsonIgnore
    private Boolean isChecked = false;
}
