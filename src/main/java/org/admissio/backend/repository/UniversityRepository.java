package org.admissio.backend.repository;

import org.admissio.backend.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University, Long> {

    @Query("""
            SELECT u
            FROM University u
            WHERE LOWER(u.universityRegion.region) = LOWER(:city)
            """)
    List<University> findByCityIgnoreCase(String city);

    @Query("""
            SELECT DISTINCT u.universityRegion.region
            FROM University u
            """)
    List<String> findAllDistinctCities();
}
