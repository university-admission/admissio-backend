package org.admissio.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.admissio.backend.service.BulkInsertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/upload")
@Tag(name = "Завантаження даних", description = "API для масового завантаження даних з CSV файлів")
@SecurityRequirement(name = "bearerAuth")
public class UploadDataController {
    private final BulkInsertService bulkInsertService;

    @Operation(
            summary = "Завантажити/замінити дані про заяви",
            description = "Приймає CSV файл для повного оновлення даних про заяви вступників. Старі дані видаляються."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Дані успішно завантажено",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Дані успішно завантажено!"))),
            @ApiResponse(responseCode = "400", description = "Поганий запит: файл порожній або не є CSV",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Дозволено завантажувати лише CSV файли."))),
            @ApiResponse(responseCode = "401", description = "Неавторизований доступ", content = @Content),
            @ApiResponse(responseCode = "403", description = "Доступ заборонено (недостатньо прав)", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @PostMapping( value = "/applications", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadApplications(@RequestParam("file") MultipartFile file){
        if(file.isEmpty())
            return new ResponseEntity<>("Файл не може бути порожнім.", HttpStatus.BAD_REQUEST);

        String contentType = file.getContentType();
        if (contentType == null || !contentType.equals("text/csv"))
            return new ResponseEntity<>("Дозволено завантажувати лише CSV файли.", HttpStatus.BAD_REQUEST);


        try {
            bulkInsertService.replaceApplication(file.getInputStream());
            return new ResponseEntity<>("Дані успішно завантажено!", HttpStatus.CREATED);
        }catch (IOException e) {
            //log.error("Помилка читання файлу під час завантаження.", e);
            return new ResponseEntity<>("Не вдалося прочитати дані з файлу.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            //log.error("Помилка під час обробки завантажених даних.", e);
            return new ResponseEntity<>("Внутрішня помилка сервера при обробці даних.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Завантажити/замінити дані про студентів",
            description = "Приймає CSV файл для повного оновлення даних про студентів. Старі дані видаляються."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Дані успішно завантажено",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Дані успішно завантажено!"))),
            @ApiResponse(responseCode = "400", description = "Поганий запит: файл порожній або не є CSV",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Дозволено завантажувати лише CSV файли."))),
            @ApiResponse(responseCode = "401", description = "Неавторизований доступ", content = @Content),
            @ApiResponse(responseCode = "403", description = "Доступ заборонено (недостатньо прав)", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @PostMapping( value = "/students", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadStudents(@RequestParam("file") MultipartFile file){
        if(file.isEmpty())
            return new ResponseEntity<>("Файл не може бути порожнім.", HttpStatus.BAD_REQUEST);

        String contentType = file.getContentType();
        if (contentType == null || !contentType.equals("text/csv"))
            return new ResponseEntity<>("Дозволено завантажувати лише CSV файли.", HttpStatus.BAD_REQUEST);


        try {
            bulkInsertService.replaceStudents(file.getInputStream());
            return new ResponseEntity<>("Дані успішно завантажено!", HttpStatus.CREATED);
        }catch (IOException e) {
            //log.error("Помилка читання файлу під час завантаження.", e);
            return new ResponseEntity<>("Не вдалося прочитати дані з файлу.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            //log.error("Помилка під час обробки завантажених даних.", e);
            return new ResponseEntity<>("Внутрішня помилка сервера при обробці даних.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
