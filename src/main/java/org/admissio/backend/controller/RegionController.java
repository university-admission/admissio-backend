package org.admissio.backend.controller;

import lombok.RequiredArgsConstructor;
import org.admissio.backend.service.UniversityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
public class RegionController {

    private final UniversityService universityService;

    @GetMapping
    public List<String> getAllRegions() {
        return universityService.getAllCities();
    }
}
