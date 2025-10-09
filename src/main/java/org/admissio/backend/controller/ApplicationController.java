package org.admissio.backend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.admissio.backend.entity.Application;
import org.admissio.backend.entity.QuotaType;
import org.admissio.backend.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
@SecurityRequirements
public class ApplicationController {
    private final ApplicationService applicationService;

    @GetMapping("/student")
    public ResponseEntity<List<Application>> findAllByStudentId(@RequestParam Long studentId) {
        return new ResponseEntity<>(applicationService.findAllByStudentId(studentId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Application>> findAllByOffer(@RequestParam Long offerId, @RequestParam QuotaType quotaType, @RequestParam Boolean isBudget) {
        return new ResponseEntity<>(applicationService.findAllByQuotaTypeAndOfferId(quotaType, offerId, isBudget), HttpStatus.OK);
    }
}
