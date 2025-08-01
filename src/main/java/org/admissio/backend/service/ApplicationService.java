package org.admissio.backend.service;

import lombok.RequiredArgsConstructor;
import org.admissio.backend.entity.Application;
import org.admissio.backend.entity.QuotaType;
import org.admissio.backend.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public List<Application> findAllByQuotaTypeAndOfferId(QuotaType quotaType, Long offerId,  Boolean isBudget) {
        if (!isBudget) {
            List<Application> applications = new ArrayList<>();
            applications.addAll(applicationRepository.findAllByQuotaTypeAndOfferIdAndIsBudget(QuotaType.GENERAL, offerId, false));
            applications.addAll(applicationRepository.findAllByQuotaTypeAndOfferIdAndIsBudget(QuotaType.QUOTA_1, offerId, false));
            applications.addAll(applicationRepository.findAllByQuotaTypeAndOfferIdAndIsBudget(QuotaType.QUOTA_2, offerId, false));
            return applications;
        }

        return applicationRepository.findAllByQuotaTypeAndOfferIdAndIsBudget(QuotaType.GENERAL, offerId, true);
    }

    public List<Application> findAllByStudentId(Long studentId) {
        return applicationRepository.findAllByStudentId(studentId);
    }
}
