package ugurayebe.fun.database.notTable;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class classroom {
    public static void main() {
        String sql = "CREATE TABLE IF NOT EXISTS Classroom ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "Type VARCHAR(255) NOT NULL UNIQUE,"
                + "Capacity INT NOT NULL,"
                + "Custom INT NOT NULL"
                + ")";
        jdbcTemplate.execute(sql);
    }
}
