package org.admissio.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.admissio.backend.entity.EducationForm;
import org.admissio.backend.entity.Major;
import org.admissio.backend.entity.Offer;

public record OfferDTO(
        @Schema(description = "Унікальний ID пропозиції", example = "1") Long id,
        @Schema(description = "ID пропозиції в системі ЄДЕБО", example = "123456") Long edboId,
        @Schema(description = "Назва пропозиції", example = "Комп'ютерні науки (ПМ-1)") String name,
        @Schema(description = "Об'єкт спеціальності") Major major,
        @Schema(description = "Назва університету", example = "Львівський національний університет імені Івана Франка") String universityName,
        @Schema(description = "Назва регіону", example = "Львівська область") String regionName,
        @Schema(description = "Назва факультету", example = "Факультет прикладної математики та інформатики") String faculty,
        @Schema(description = "Назва освітньої програми", example = "Інженерія програмного забезпечення") String educationalProgram,
        @Schema(description = "Ціна за рік навчання (для контракту)", example = "45000") Integer price,
        @Schema(description = "Форма навчання") EducationForm educationForm,

        @Schema(description = "Кількість бюджетних місць", example = "50") Integer budgetPlaces,
        @Schema(description = "Кількість поданих заяв на бюджет", example = "350") Integer budgetApplications,
        @Schema(description = "Кількість заяв що претендують саме на цю пропозицію на бюджет", example = "50") Integer budgetPlacesCount,
        @Schema(description = "Мінімальний прохідний бал на бюджет зараз, =0 якщо budgetPlacesCount < budgetPlaces", example = "185.5") Double minBudgetScore,

        @Schema(description = "Кількість контрактних місць", example = "100") Integer contractPlaces,
        @Schema(description = "Кількість поданих заяв на контракт", example = "200") Integer contractApplications,
        @Schema(description = "Кількість заяв що претендують саме на цю пропозицію на контракт", example = "80") Integer contractPlacesCount,
        @Schema(description = "Мінімальний прохідний бал на контракт зараз, =0 якщо contractPlacesCount < contractPlaces", example = "160.0") Double minContractScore,

        @Schema(description = "Кількість місць квоти 1", example = "10") Integer quota1Places,
        @Schema(description = "Кількість поданих заяв на квоту 1", example = "20") Integer quota1Applications,
        @Schema(description = "Кількість заяв що претендують саме на цю пропозицію на квоту 1", example = "7") Integer quota1PlacesCount,
        @Schema(description = "Мінімальний прохідний бал на квоту 1 зараз, =0 якщо quota1PlacesCount < quota1Places", example = "160.0") Double minQuota1Score,

        @Schema(description = "Кількість місць квоти 2", example = "10") Integer quota2Places,
        @Schema(description = "Кількість поданих заяв на квоту 2", example = "20") Integer quota2Applications,
        @Schema(description = "Кількість заяв що претендують саме на цю пропозицію на квоту 2", example = "7") Integer quota2PlacesCount,
        @Schema(description = "Мінімальний прохідний бал на квоту 1 зараз, =0 якщо quota2PlacesCount < quota2Places", example = "160.0") Double minQuota2Score,

        @Schema(description = "Мінімальний бал з української мови", example = "140") Integer minUkLangScore,
        @Schema(description = "Мінімальний бал з математики", example = "140") Integer minMathScore,
        @Schema(description = "Мінімальний бал з історії україни", example = "140") Integer minHistoryScore,
        @Schema(description = "Мінімальний бал з української літератури", example = "140") Integer minUkLitScore,
        @Schema(description = "Мінімальний бал з іноземної мови", example = "140") Integer minForeignLangScore,
        @Schema(description = "Мінімальний бал з біології", example = "140") Integer minBiologyScore,
        @Schema(description = "Мінімальний бал з географії", example = "140") Integer minGeographyScore,
        @Schema(description = "Мінімальний бал з фізики", example = "140") Integer minPhysicsScore,
        @Schema(description = "Мінімальний бал з хімії", example = "140") Integer minChemistryScore,
        @Schema(description = "Мінімальний бал з творчого конкурсу", example = "140") Integer minCompetitionScore,
        @Schema(description = "Мінімальний бал з для всієї заявки", example = "140") Integer minApplicationScore,

        @Schema(description = "Додаткові бали (напр., за олімпіади)", example = "10") Integer additionalPoints,
        @Schema(description = "Регіональний коефіцієнт, що застосовується до конкурсного балу", example = "1.04") Double regionCoef
) {
    public static OfferDTO fromEntity(Offer offer) {
        return new OfferDTO(
                offer.getId(),
                offer.getEdboId(),
                offer.getName(),
                offer.getMajor(),
                offer.getUniversity().getUniversityName(),
                offer.getUniversity().getUniversityRegion().getRegion(),
                offer.getFaculty(),
                offer.getEducationalProgram(),
                offer.getPrice(),
                offer.getEducationForm(),
                offer.getBudgetPlaces(),
                offer.getBudgetApplications(),
                offer.getBudgetPlacesCount(),
                offer.getMinBudgetScore(),
                offer.getContractPlaces(),
                offer.getContractApplications(),
                offer.getContractPlacesCount(),
                offer.getMinContractScore(),
                offer.getQuota1Places(),
                offer.getQuota1Applications(),
                offer.getQuota1PlacesCount(),
                offer.getMinQuota1Score(),
                offer.getQuota2Places(),
                offer.getQuota2Applications(),
                offer.getQuota2PlacesCount(),
                offer.getMinQuota2Score(),
                offer.getMinUkLangScore(),
                offer.getMinMathScore(),
                offer.getMinHistoryScore(),
                offer.getMinUkLitScore(),
                offer.getMinForeignLangScore(),
                offer.getMinBiologyScore(),
                offer.getMinGeographyScore(),
                offer.getMinPhysicsScore(),
                offer.getMinChemistryScore(),
                offer.getMinCompetitionScore(),
                offer.getMinApplicationScore(),
                offer.getAdditionalPoints(),
                offer.getRegionCoef()
        );
    }
}
