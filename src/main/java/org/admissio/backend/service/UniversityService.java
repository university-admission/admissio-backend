package org.admissio.backend.service;

import lombok.RequiredArgsConstructor;
import org.admissio.backend.entity.University;
import org.admissio.backend.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityService {

    private final UniversityRepository universityRepository;

    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    public List<University> getUniversitiesByCity(String city) {
        return universityRepository.findByCityIgnoreCase(city);
    }

    public List<String> getAllCities() {
        return universityRepository.findAllDistinctCities();
    }
}
