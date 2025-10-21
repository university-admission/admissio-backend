package org.admissio.backend.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Форма навчання", enumAsRef = true, example = "FULL_TIME")
public enum EducationForm {
    FULL_TIME,  // Денна
    PART_TIME,  // Заочна
    EVENING,    // Вечірня
    DISTANCE    // Дистанційна
}
