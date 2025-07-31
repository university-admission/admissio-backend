package org.admissio.backend.service;

import lombok.RequiredArgsConstructor;
import org.admissio.backend.entity.Offer;
import org.admissio.backend.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;

    public List<Offer> findAllByIds(List<Long> offerIds) {
        if (offerIds == null)
            return (List<Offer>) offerRepository.findAll();
        else
            return offerRepository.findAllByIdIn(offerIds);
    }

    public List<Offer> findAllByParams(Long majorId, Long regionId, Long universityId, String educationForm) {
        return offerRepository.findAllByParams(majorId, regionId, universityId, educationForm);
    }
}
