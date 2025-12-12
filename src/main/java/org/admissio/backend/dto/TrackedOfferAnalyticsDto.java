package org.admissio.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrackedOfferAnalyticsDto {
    @NotNull
    Long offerId;

    @NotNull
    String universityName;

    @NotNull
    String majorName;

    @NotNull
    String facultyName;

    Double userScore;

    Integer rankActual;

    Integer rankAll;

    @NotNull
    Integer totalApplications;

    @NotNull
    Integer places;

    @NotNull
    Double passingScore;
}
