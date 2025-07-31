package org.admissio.backend.repository;

import org.admissio.backend.entity.Offer;

import java.util.List;

public interface OfferCustomRepository {
    List<Offer> findAllByParams(Long majorId, Long cityId, Long universityId, String educationForm);
}
