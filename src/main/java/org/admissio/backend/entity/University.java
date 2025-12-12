package org.admissio.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "universities")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Інформація про університет")
public class University {

    @Schema(description = "Унікальний ідентифікатор університету", example = "1")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Schema(description = "Повна назва університету", example = "Київський національний університет імені Тараса Шевченка")
    @NonNull
    @Column(name = "university_name", nullable = false)
    private String universityName;

    @Schema(description = "Унікальний код університету в системі ЄДЕБО", example = "1234")
    @NonNull
    @Column(name = "university_code", nullable = false, unique = true)
    private Integer universityCode;

    @JsonIgnore
    @ManyToOne
    @NonNull
    @JoinColumn(name = "university_region_id", referencedColumnName = "id", nullable = false)
    private UniversityRegion universityRegion;

    @JsonIgnore
    @OneToMany(mappedBy = "university")
    private Set<Offer> offers;
}
