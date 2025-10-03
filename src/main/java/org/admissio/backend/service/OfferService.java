package org.admissio.backend.service;

import lombok.RequiredArgsConstructor;
import org.admissio.backend.dto.OfferDTO;
import org.admissio.backend.entity.EducationForm;
import org.admissio.backend.entity.Offer;
import org.admissio.backend.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;

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
}
