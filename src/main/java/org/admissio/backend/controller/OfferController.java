package org.admissio.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.admissio.backend.dto.OfferDTO;
import org.admissio.backend.entity.EducationForm;
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
@SecurityRequirements
@Tag(name = "Конкурсні пропозиції", description = "API для отримання та фільтрації конкурсних пропозицій")
public class OfferController {
    private final OfferService offerService;

    @Operation(
            summary = "Відфільтрувати конкурсні пропозиції",
            description = "Повертає список конкурсних пропозицій за набором необов'язкових параметрів фільтрації."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успішне отримання відфільтрованого списку",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OfferDTO.class))) })
    })
    @GetMapping("/filter")
    public ResponseEntity<List<OfferDTO>> findAllByParam(
            @Parameter(description = "ID спеціальності") @RequestParam(value = "majorId", required = false) Long majorId,
            @Parameter(description = "ID регіону") @RequestParam(value = "regionId", required = false) Long regionId,
            @Parameter(description = "ID університету") @RequestParam(value = "universityId", required = false) Long universityId,
            @Parameter(description = "Форма навчання") @RequestParam(value = "educationForm", required = false) EducationForm educationForm
    ) {
        return new ResponseEntity<>(offerService.findAllByParams(majorId, regionId, universityId, educationForm), HttpStatus.OK);
    }

    @Operation(
            summary = "Отримати всі можливі форми навчання",
            description = "Повертає список усіх доступних форм навчання для використання у фільтрах."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успішне отримання списку",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(type = "string", example = "FULL_TIME"))))
    })
    @GetMapping("/education-form")
    public ResponseEntity<List<EducationForm>> getAllEducationForms() {
        return new ResponseEntity<>(offerService.getAllEducationForms(), HttpStatus.OK);
    }
}
