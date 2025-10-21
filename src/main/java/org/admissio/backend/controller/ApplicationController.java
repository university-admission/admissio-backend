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
@Tag(name = "Заяви", description = "API для отримання інформації про заяви вступників")
@SecurityRequirements
public class ApplicationController {
    private final ApplicationService applicationService;

    @Operation(
            summary = "Знайти всі заяви за ID студента",
            description = "Повертає список усіх заяв, поданих конкретним студентом."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успішне отримання списку",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Application.class))) })
    })
    @GetMapping("/student")
    public ResponseEntity<List<Application>> findAllByStudentId(
            @Parameter(description = "Унікальний ID студента", required = true, example = "1")
            @RequestParam Long studentId) {
        return new ResponseEntity<>(applicationService.findAllByStudentId(studentId), HttpStatus.OK);
    }

    @Operation(
            summary = "Знайти заяви за конкурсною пропозицією та квотою",
            description = "Повертає список заяв, поданих на конкретну конкурсну пропозицію (offerId), " +
                    "з урахуванням типу квоти та форми фінансування (бюджет/контракт)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успішне отримання списку",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Application.class))) })
    })
    @GetMapping("")
    public ResponseEntity<List<Application>> findAllByOffer(
            @Parameter(description = "ID конкурсної пропозиції", required = true, example = "202")
            @RequestParam Long offerId,
            @Parameter(description = "Тип квоти (напр., QUOTA_1, GENERAL)", required = true, example = "GENERAL")
            @RequestParam QuotaType quotaType,
            @Parameter(description = "Форма фінансування (true - бюджет, false - контракт)", required = true, example = "true")
            @RequestParam Boolean isBudget
    ) {
        return new ResponseEntity<>(applicationService.findAllByQuotaTypeAndOfferId(quotaType, offerId, isBudget), HttpStatus.OK);
    }
}
