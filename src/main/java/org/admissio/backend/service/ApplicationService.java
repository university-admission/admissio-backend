package org.admissio.backend.service;

import lombok.RequiredArgsConstructor;
import org.admissio.backend.dto.ApplicationDto;
import org.admissio.backend.dto.StudentApplicationDto;
import org.admissio.backend.entity.Application;
import org.admissio.backend.entity.QuotaType;
import org.admissio.backend.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public List<ApplicationDto> findAllByQuotaTypeAndOfferId(QuotaType quotaType, Long offerId, Boolean isBudget) {
        if (!isBudget) {
            List<Application> applications = applicationRepository.findAllByOfferIdAndIsBudget(offerId, false);

            return applications.stream()
                    .map(this::mapToApplicationDto)
                    .sorted(Comparator.comparingDouble(ApplicationDto::getScore).reversed())
                    .toList();
        }

        return applicationRepository.findAllByQuotaTypeAndOfferIdAndIsBudget(quotaType, offerId, true)
                .stream()
                .map(this::mapToApplicationDto)
                .sorted(Comparator.comparingDouble(ApplicationDto::getScore).reversed())
                .toList();
    }

    private ApplicationDto mapToApplicationDto(Application application) {
        return new ApplicationDto(
                application.getStudent().getId(),
                application.getStudent().getFullName(),
                application.getScore(),
                application.getPriority(),
                application.getIsActual(),
                application.getIsCounted()
        );
    }

    public List<StudentApplicationDto> findAllByStudentId(Long studentId) {
        return applicationRepository.findAllByStudentId(studentId)
                .stream()
                .map(this::mapToStudentApplicationDto)
                .sorted(Comparator.comparingInt(StudentApplicationDto::getPriority))
                .toList();
    }

    private StudentApplicationDto mapToStudentApplicationDto(Application application) {
        return new StudentApplicationDto(
                application.getPriority(),
                application.getIsActual(),
                application.getIsCounted(),
                application.getScore(),
                application.getQuotaType(),
                application.getIsBudget(),
                application.getOffer().getUniversity().getUniversityName(),
                application.getOffer().getFaculty(),
                application.getOffer().getMajor().getMajorName()
        );
    }
}
