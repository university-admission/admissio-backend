package org.admissio.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.admissio.backend.dto.OfferDTO;
import org.admissio.backend.dto.TrackedOfferAnalyticsDto;
import org.admissio.backend.dto.TrackedOffersRequestDto;
import org.admissio.backend.entity.Application;
import org.admissio.backend.entity.EducationForm;
import org.admissio.backend.entity.Offer;
import org.admissio.backend.repository.OfferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferService {
    private final OfferRepository offerRepository;
    public final ScoreCalculationService scoreCalculationService;

    public List<OfferDTO> findAllByParams(Long majorId, Long regionId, Long universityId, EducationForm educationForm) {
        return offerRepository.findAllByParams(majorId, regionId, universityId, educationForm)
                .stream()
                .map(OfferDTO::fromEntity)
                .toList();
    }

    public List<EducationForm> getAllEducationForms() {
        return Arrays.stream(EducationForm.values()).toList();
    }

    @Transactional
    public List<TrackedOfferAnalyticsDto> getAllTrackedOffers(TrackedOffersRequestDto requestDto) {
        List<TrackedOfferAnalyticsDto> trackedOffers = new ArrayList<>();

        for (Offer offer : offerRepository.findAllById(requestDto.getOfferIds())) {
            try {
                String universityName = offer.getUniversity().getUniversityName();
                String majorName = offer.getMajor().getMajorName();
                String offerName = offer.getName();
                String facultyName = offer.getFaculty();
                EducationForm educationForm = offer.getEducationForm();

                Double userScore = scoreCalculationService.calculateScore(offer.getId(), requestDto.getScoreCalculationRequestDto()).orElse(null);

                Integer places = getPlacesCount(offer, requestDto);

                if (userScore == null) {
                    log.warn("Score calculation failed for offer {}", offer.getId());
                    trackedOffers.add(new TrackedOfferAnalyticsDto(
                            offer.getId(), universityName, majorName, offerName, facultyName, educationForm,
                            0.0, 0, 0, places
                    ));
                    continue;
                }

                List<Application> relevantApplications = offer.getApplications().stream()
                        .filter(app -> app.getIsBudget() == requestDto.getIsBudget() &&
                                app.getQuotaType() == requestDto.getQuotaType() &&
                                app.getIsCounted())
                        .toList();


                long betterThanMeAll = relevantApplications.stream()
                        .filter(app -> app.getScore() > userScore)
                        .count();
                Integer rankAll = (int) betterThanMeAll + 1;

                long betterThanMeActual = relevantApplications.stream()
                        .filter(app -> app.getIsActual() && app.getScore() > userScore)
                        .count();
                Integer rankActual = (int) betterThanMeActual + 1;

                trackedOffers.add(new TrackedOfferAnalyticsDto(
                        offer.getId(),
                        universityName,
                        majorName,
                        offerName,
                        facultyName,
                        educationForm,
                        userScore,
                        rankActual,
                        rankAll,
                        places
                ));

            } catch (Exception e) {
                log.error("Error processing offer {}", offer.getId(), e);
            }
        }

        return trackedOffers;
    }

    private Integer getPlacesCount(Offer offer, TrackedOffersRequestDto requestDto) {
        if (!requestDto.getIsBudget()) return offer.getContractPlaces();
        return switch (requestDto.getQuotaType()) {
            case GENERAL -> offer.getBudgetPlaces();
            case QUOTA_1 -> offer.getQuota1Places();
            case QUOTA_2 -> offer.getQuota2Places();
        };
    }
}
