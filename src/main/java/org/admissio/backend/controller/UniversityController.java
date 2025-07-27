package org.admissio.backend.controller;

import lombok.RequiredArgsConstructor;
import org.admissio.backend.entity.University;
import org.admissio.backend.service.UniversityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universities")
@RequiredArgsConstructor
public class UniversityController {

    private final UniversityService universityService;

    @GetMapping
    public List<University> getUniversities(@RequestParam(required = false) String city) {
        if (city != null && !city.isBlank()) {
            return universityService.findAllByRegion(city);
        }
        return universityService.findAll();
    }
}
