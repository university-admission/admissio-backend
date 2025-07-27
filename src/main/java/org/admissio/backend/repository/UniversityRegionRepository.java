package org.admissio.backend.repository;

import org.admissio.backend.entity.UniversityRegion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UniversityRegionRepository  extends CrudRepository<UniversityRegion, Long> {
    List<UniversityRegion> findByRegionContainingIgnoreCase(String region);
}
