package org.admissio.backend.controller;

import lombok.RequiredArgsConstructor;
import org.admissio.backend.entity.Major;
import org.admissio.backend.service.MajorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/majors")
@RequiredArgsConstructor
public class MajorController {
    private final MajorService majorService;

    @GetMapping
    public ResponseEntity<List<Major>> getAllMajors() {
        return new ResponseEntity<>(majorService.findAll(), HttpStatus.OK);
    }
}
