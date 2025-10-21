package org.admissio.backend.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Тип квоти, за якою вступник бере участь у конкурсі.", enumAsRef = true, example = "GENERAL")
public enum QuotaType {
    GENERAL,
    QUOTA_1,
    QUOTA_2,
}
