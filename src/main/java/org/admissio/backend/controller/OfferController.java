package org.admissio.backend.controller;

import lombok.RequiredArgsConstructor;
import org.admissio.backend.entity.Offer;
import org.admissio.backend.service.OfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/offers")
@RequiredArgsConstructor
public class OfferController {
    private final OfferService offerService;

    @GetMapping("")
    public ResponseEntity<List<Offer>> findAllByIds(@RequestParam(value = "ids", required = false) List<Long> ids) {
        return new ResponseEntity<>(offerService.findAllByIds(ids), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Offer>> findAllByParam(
            @RequestParam(value = "majorId", required = false) Long majorId,
            @RequestParam(value = "regionId", required = false) Long regionId,
            @RequestParam(value = "universityId", required = false) Long universityId,
            @RequestParam(value = "educationForm", required = false) String educationForm) {
        return new ResponseEntity<>(offerService.findAllByParams(majorId, regionId, universityId, educationForm),  HttpStatus.OK);
    }
}
