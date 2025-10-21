package org.admissio.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Запит на автентифікацію користувача")
public class LoginRequest {

    @Schema(description = "Ім'я користувача (логін)", example = "login", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @NotNull
    private String username;

    @Schema(description = "Пароль користувача", example = "password",  requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @NotNull
    private String password;
}
