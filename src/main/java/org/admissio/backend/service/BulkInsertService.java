package org.admissio.backend.service;

import lombok.AllArgsConstructor;
import org.postgresql.PGConnection;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;

@AllArgsConstructor
@Service
public class BulkInsertService {
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void replaceApplication(InputStream csvInputStream){
        try {
            jdbcTemplate.execute((Connection conn) -> {
                try {
                    PGConnection pgConnection = conn.unwrap(PGConnection.class);
                    CopyManager copyManager = new CopyManager((BaseConnection) pgConnection);

                    try (var stmt = conn.createStatement()) {
                        stmt.execute("CREATE TEMP TABLE applications_temp (LIKE applications INCLUDING DEFAULTS) ON COMMIT DROP");

                        try (var reader = new InputStreamReader(csvInputStream)) {
                            copyManager.copyIn(
                                    "COPY applications_temp(student_id, offer_id, score, priority, is_budget, quota_type, is_actual, is_counted, is_checked) " +
                                            "FROM STDIN WITH (FORMAT csv, HEADER false, ENCODING 'UTF8')",
                                    reader
                            );
                        }

                        //Помилка компілятора, бо не бачить temp table. Все працює
                        stmt.execute("TRUNCATE TABLE applications RESTART IDENTITY;");
                        stmt.execute("INSERT INTO applications SELECT * FROM applications_temp;");
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Помилка під час виконання COPY операції", e);
                }
                return null;
            });
        } catch (Exception e) {
            throw new RuntimeException("Не вдалося виконати пакетне оновлення даних.", e);
        }
    }

    @Transactional
    public void replaceStudents(InputStream csvInputStream){
        try {
            jdbcTemplate.execute((Connection conn) -> {
                try {
                    PGConnection pgConnection = conn.unwrap(PGConnection.class);
                    CopyManager copyManager = new CopyManager((BaseConnection) pgConnection);

                    try (var stmt = conn.createStatement()) {
                        stmt.execute("CREATE TEMP TABLE students_temp (LIKE students INCLUDING DEFAULTS) ON COMMIT DROP");

                        try (var reader = new InputStreamReader(csvInputStream)) {
                            copyManager.copyIn(
                                    "COPY students_temp(full_name, raw_score) " +
                                            "FROM STDIN WITH (FORMAT csv, HEADER false, ENCODING 'UTF8')",
                                    reader
                            );
                        }

                        //Помилка компілятора, бо не бачить temp table. Все працює
                        stmt.execute("TRUNCATE TABLE students RESTART IDENTITY CASCADE;");
                        stmt.execute("INSERT INTO students SELECT * FROM students_temp;");
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Помилка під час виконання COPY операції", e);
                }
                return null;
            });
        } catch (Exception e) {
            throw new RuntimeException("Не вдалося виконати пакетне оновлення даних.", e);
        }
    }
}
