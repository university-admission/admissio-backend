package org.admissio.backend.repository;

import org.admissio.backend.entity.Application;
import org.admissio.backend.entity.QuotaType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
    @EntityGraph(attributePaths = {"student"})
    List<Application> findAllByQuotaTypeAndOfferIdAndIsBudget(QuotaType quotaType, Long offerId, Boolean isBudget);

    @EntityGraph(attributePaths = {"student"})
    List<Application> findAllByOfferIdAndIsBudget(Long offerId, Boolean isBudget);

    @EntityGraph(attributePaths = {"offer", "offer.university", "offer.major"})
    List<Application> findAllByStudentId(Long studentId);
}
