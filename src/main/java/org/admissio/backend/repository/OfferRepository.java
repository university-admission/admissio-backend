package org.admissio.backend.repository;

import org.admissio.backend.entity.Offer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfferRepository extends CrudRepository<Offer, Long>, OfferCustomRepository {
    List<Offer> findAllByIdIn(List<Long> offerIds);
}