package org.admissio.backend.controller;

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
public class UploadDataController {
    private final BulkInsertService bulkInsertService;

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
