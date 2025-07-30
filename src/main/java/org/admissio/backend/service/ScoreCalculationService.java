package org.admissio.backend.service;

import lombok.RequiredArgsConstructor;
import org.admissio.backend.dto.ScoreCalculationRequestDto;
import org.admissio.backend.entity.Major;
import org.admissio.backend.entity.Offer;
import org.admissio.backend.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScoreCalculationService {
    private final OfferRepository offerRepository;

    public Optional<Double> calculateScore(Long offerId, ScoreCalculationRequestDto dto) {
        Optional<Offer> offer = offerRepository.findById(offerId);

        if (offer.isEmpty() || dto == null) {
            return Optional.empty();
        }

        Offer foundOffer = offer.get();
        Major foundMajor = foundOffer.getMajor();

        double ukLanguageCoef = foundMajor.getUkLanguageCoef();
        double mathCoef = foundMajor.getMathCoef();
        double historyCoef = foundMajor.getHistoryCoef();
        double maxElectiveCoef = foundMajor.getMaxElectiveCoef();
        Double electiveSubjectCoef = foundMajor.getCoefForElectiveSubjectByName(dto.getElectiveSubject().getSubjectName());

        if(electiveSubjectCoef == null) {
            return Optional.empty();
        }

        double numerator = ukLanguageCoef*dto.getMandatorySubjects().getUkrainian() +
                            mathCoef*dto.getMandatorySubjects().getMath() +
                            historyCoef*dto.getMandatorySubjects().getHistory() +
                            electiveSubjectCoef*dto.getElectiveSubject().getScore();

        double denominator = ukLanguageCoef + mathCoef + historyCoef + (maxElectiveCoef+electiveSubjectCoef)/2;

        if(dto.getCreativeCompetitionScore() != null) {
            numerator += foundMajor.getCompetitionCoef()*dto.getCreativeCompetitionScore();
            denominator += foundMajor.getCompetitionCoef();
        }

        double score = numerator/denominator;

        score *= foundMajor.getMajorCoef();
        score *= foundOffer.getRegionCoef();

        score += foundOffer.getAdditionalPoints();

        return Optional.of(score);
    }
}
