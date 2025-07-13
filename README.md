# admissio-backend
Сервіс бекенду для проєкту Admissio — система прогнозування шансів вступу до університету.

## Технології
- Java 24
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

## Запуск локально
1. Запусти PostgreSQL (локально або через Docker).
2. Налаштуй підключення у `src/main/resources/application.properties`.
3. Запусти проект:
```bash
./mvnw spring-boot:run
```

### Тестування
```bash
./mvnw test
```