package org.admissio.backend.repository;

import lombok.NonNull;
import org.admissio.backend.entity.University;
import org.admissio.backend.entity.UniversityRegion;
import org.springframework.data.domain.Limit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UniversityRepository extends CrudRepository<University, Long> {
    List<University> findAllByUniversityRegion(@NonNull UniversityRegion universityRegion);
}
