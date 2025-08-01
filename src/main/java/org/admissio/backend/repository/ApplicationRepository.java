package org.admissio.backend.repository;

import org.admissio.backend.entity.Application;
import org.admissio.backend.entity.QuotaType;
import org.admissio.backend.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
    List<Application> findAllByQuotaTypeAndOfferIdAndIsBudget(QuotaType quotaType, Long offerId, Boolean isBudget);

    List<Application> findAllByStudentId(Long studentId);
}
