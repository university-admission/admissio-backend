package org.admissio.backend.validation;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Object> handleTokenExpiredException(TokenExpiredException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(
                        "status", HttpStatus.UNAUTHORIZED.value(),
                        "error", "Unauthorized",
                        "message", "Термін дії вашої сесії закінчився. Будь ласка, увійдіть знову."
                ));
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<Object> handleJwtVerificationException(JWTVerificationException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of(
                        "status", HttpStatus.FORBIDDEN.value(),
                        "error", "Forbidden",
                        "message", "Невалідний токен доступу."
                ));
    }
}
