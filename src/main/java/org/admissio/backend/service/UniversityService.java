package org.admissio.backend.service;

import lombok.RequiredArgsConstructor;
import org.admissio.backend.entity.University;
import org.admissio.backend.entity.UniversityRegion;
import org.admissio.backend.repository.UniversityRegionRepository;
import org.admissio.backend.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class UniversityService {

    private final UniversityRepository universityRepository;
    private final UniversityRegionRepository universityRegionRepository;

    public List<University> findAllByRegion(String region) {
       Set<University> universities = new HashSet<>();

       for (UniversityRegion universityRegion : universityRegionRepository.findByRegionContainingIgnoreCase(region)) {
           universities.addAll(universityRegion.getUniversities());
       }

       return new ArrayList<>(universities);
    }

    public List<University> findAll(){
        return (List<University>) universityRepository.findAll();
    }

}
