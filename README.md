# admissio-backend
Сервіс бекенду для проєкту **Admissio** — система прогнозування шансів вступу до університету.

## Технології
- Java 24
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

## Запуск локально
1. Запусти PostgreSQL (локально або через Docker).
2. Запусти проект:
```bash
./mvnw spring-boot:run
```

### Тестування
```bash
./mvnw test
```

## Запуск через Docker
```bash
docker build -t admissio-backend .
docker run -p 8080:8080 admissio-backend
```

## Перегляд ендпоіндів
http://localhost:8080/swagger-ui/index.html#/

## Запуск усіх сервісів через Docker Compose
Усі репозиторії мають бути клоновані в 1 деку, це має виглядати так:
 - /admissio-backend
 - /admissio-frontend
 - /admissio-scraper

Далі запустити усі сервіси, або лише ті, які потрібні
```bash
docker-compose up backend frontend scraper
```

## Запуск в IntelliJ
Для зручного використання, рекомендую додати такі конфігурації:
 - Spring-Boot
   - Backend
     ![backend config.png](./img%20for%20readme/Backend%20config.png)
   - Scraper
     ![scraper config](./img%20for%20readme/Scraper%20config.png)
 - Web
   - Frontend
     ![frontend config](./img%20for%20readme/Frontend%20config.png)
 - Docker
   ![docker config](./img%20for%20readme/Docker%20config.png)
Тут легко змінювати сервіси, які підіймаються

## Перегляд БД
1. Перевірити чи не зайнятий порт 5432, і чи не запущена Postgres локально
    - Для перевірки, у Win+R перейти по services.msc, знайти Postgres, і вимкнути якщо є
2. Налаштувати підключення через вкладку Database
    - Вибрати такі параметри:
    - 
      | **Field** | **Value**   |
      |-----------|-------------|
      | Host      | localhost   |
      | Port      | 5432        |
      | User      | admissio    |
      | Password  | password    |
      | Database  | admissio_db |