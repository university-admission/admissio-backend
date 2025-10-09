package org.admissio.backend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.admissio.backend.entity.UniversityRegion;
import org.admissio.backend.service.UniversityRegionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
@SecurityRequirements
public class RegionController {

    private final UniversityRegionService universityRegionService;

    @GetMapping
    public ResponseEntity<List<UniversityRegion>> getAllRegions() {
        return new ResponseEntity<>(universityRegionService.findAll(), HttpStatus.OK);
    }
}
