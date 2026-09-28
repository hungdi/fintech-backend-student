package com.sparta.fintech.ledger.support;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** test 프로필의 전용 H2 DB에서 테스트 데이터를 삭제하는 초기화 코드입니다. */
public final class TestDatabaseReset {
    private TestDatabaseReset() {}

    public static void clear(DataSource dataSource) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String url = connection.getMetaData().getURL();
            if (!url.equals("jdbc:h2:mem:fintech_student") && !url.startsWith("jdbc:h2:mem:fintech_student;")) {
                throw new IllegalStateException("fintech_student 메모리 DB에서만 초기화할 수 있습니다.");
            }
            List<String> tables = new ArrayList<>();
            try (var rows = connection.getMetaData().getTables(null, null, "%", new String[]{"TABLE", "BASE TABLE"})) {
                while (rows.next()) {
                    if ("public".equalsIgnoreCase(rows.getString("TABLE_SCHEM"))) tables.add(rows.getString("TABLE_NAME"));
                }
            }
            try (var statement = connection.createStatement()) {
                statement.execute("SET REFERENTIAL_INTEGRITY FALSE");
                try {
                    for (String table : tables) statement.executeUpdate("DELETE FROM \"" + table.replace("\"", "\"\"") + "\"");
                } finally {
                    statement.execute("SET REFERENTIAL_INTEGRITY TRUE");
                }
            }
        }
    }
}
