package ugurayebe.fun.database.notTable;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class title {
    public static void main() {
        String sql = "CREATE TABLE IF NOT EXISTS Title ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "Name VARCHAR(255) NOT NULL UNIQUE"
                + ")";
        jdbcTemplate.execute(sql);
    }
}
