package org.admissio.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "university_regions")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class UniversityRegion {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Schema(description = "Унікальний ідентифікатор регіону", example = "1")
    private Long id;

    @NonNull
    @Column(name = "region", nullable = false)
    @Schema(description = "Назва регіону", example = "Київська область")
    private String region;

    @JsonIgnore
    @OneToMany(mappedBy = "universityRegion")
    private Set<University> universities;
}
