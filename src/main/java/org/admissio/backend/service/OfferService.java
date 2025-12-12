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

    public List<OfferDTO> findAllByIds(List<Long> offerIds) {
        if (offerIds == null) {
            List<Offer> offers = (List<Offer>) offerRepository.findAll();
            return offers.stream()
                    .map(OfferDTO::fromEntity)
                    .toList();
        }
        else
            return offerRepository.findAllByIdIn(offerIds)
                    .stream()
                    .map(OfferDTO::fromEntity)
                    .toList();
    }

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
                String facultyName = offer.getFaculty();

                Double userScore = scoreCalculationService.calculateScore(offer.getId(), requestDto.getScoreCalculationRequestDto()).orElse(null);

                Integer places = getPlacesCount(offer, requestDto);
                Double passingScore = getPassingScore(offer, requestDto);

                if (userScore == null) {
                    log.warn("Score calculation failed for offer {}", offer.getId());
                    trackedOffers.add(new TrackedOfferAnalyticsDto(
                            offer.getId(), universityName, majorName, facultyName,
                            0.0, 0, 0, 0, places, passingScore
                    ));
                    continue;
                }

                List<Application> relevantApplications = offer.getApplications().stream()
                        .filter(app -> app.getIsBudget() == requestDto.getIsBudget() &&
                                app.getQuotaType() == requestDto.getQuotaType() &&
                                app.getIsCounted())
                        .toList();

                Integer totalApplications = relevantApplications.size();

                long betterThanMeAll = relevantApplications.stream()
                        .filter(app -> app.getScore() > userScore)
                        .count();
                Integer rankAll = (int) betterThanMeAll + 1;

                long betterThanMeActual = relevantApplications.stream()
                        .filter(Application::getIsActual)
                        .filter(app -> app.getScore() > userScore)
                        .count();
                Integer rankActual = (int) betterThanMeActual + 1;

                trackedOffers.add(new TrackedOfferAnalyticsDto(
                        offer.getId(),
                        universityName,
                        majorName,
                        facultyName,
                        userScore,
                        rankActual,
                        rankAll,
                        totalApplications,
                        places,
                        passingScore
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

    private Double getPassingScore(Offer offer, TrackedOffersRequestDto requestDto) {
        if (!requestDto.getIsBudget()) return offer.getMinContractScore();
        return switch (requestDto.getQuotaType()) {
            case GENERAL -> offer.getMinBudgetScore();
            case QUOTA_1 -> offer.getMinQuota1Score();
            case QUOTA_2 -> offer.getMinQuota2Score();
        };
    }
}
