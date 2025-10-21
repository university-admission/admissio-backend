package org.admissio.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Відповідь з JWT-токеном та інформацією про користувача")
@Data
public class JwtResponse {
    @Schema(description = "Згенерований JWT-токен доступу",
            example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc2NTI5OTgwMywiZXhwIjoxNzY1MjAzNDAzfQ.signature",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String token;

    @Schema(description = "Тип токена (завжди 'Bearer')", example = "Bearer")
    private String type = "Bearer";

    @Schema(description = "Ім'я користувача (логін), для якого видано токен",
            example = "admin",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    public JwtResponse(String accessToken, String username) {
        this.token = accessToken;
        this.username = username;
    }
}
