package ugurayebe.fun.database.notTable;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class days {
    public static void main() {
        String sql = "CREATE TABLE IF NOT EXISTS Days (" + "id INT AUTO_INCREMENT PRIMARY KEY," +
                "Day VARCHAR(50) NOT NULL UNIQUE" + ")";
        jdbcTemplate.execute(sql);


        String sqlRowCount = "SELECT COUNT(*) FROM Days";
        int currentRowCount = jdbcTemplate.queryForObject(sqlRowCount, Integer.class);

        if (currentRowCount == 0) {
            String sqlDays = "insert into Days(Day) values (?)";
            jdbcTemplate.update(sqlDays, "Pazartesi");
            jdbcTemplate.update(sqlDays, "Salı");
            jdbcTemplate.update(sqlDays, "Çarşamba");
            jdbcTemplate.update(sqlDays, "Perşembe");
            jdbcTemplate.update(sqlDays, "Cuma");
            jdbcTemplate.update(sqlDays, "Cumartesi");
            jdbcTemplate.update(sqlDays, "Pazar");
        }
    }
}
