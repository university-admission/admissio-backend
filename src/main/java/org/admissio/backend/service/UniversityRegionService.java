package org.admissio.backend.service;

import lombok.RequiredArgsConstructor;
import org.admissio.backend.entity.UniversityRegion;
import org.admissio.backend.repository.UniversityRegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityRegionService {
    private final UniversityRegionRepository universityRegionRepository;

    public List<UniversityRegion> findAll() {
        return (List<UniversityRegion>) universityRegionRepository.findAll();
    }
}
