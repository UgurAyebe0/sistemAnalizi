package ugurayebe.fun.database.notTable;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class days {
    public static void main() {
        String sql = "CREATE TABLE IF NOT EXISTS Days (" + "id INT AUTO_INCREMENT PRIMARY KEY," + "Day VARCHAR(255) NOT NULL UNIQUE," + "Confirmation BOOLEAN" + ")";
        jdbcTemplate.execute(sql);


        String sqlRowCount = "SELECT COUNT(*) FROM Days";
        int currentRowCount = jdbcTemplate.queryForObject(sqlRowCount, Integer.class);

        if (currentRowCount == 0) {
            String sqlDays = "insert into Days(Day,Confirmation) values (?,?)";
            jdbcTemplate.update(sqlDays, "Pazartesi", 0);
            jdbcTemplate.update(sqlDays, "Salı", 0);
            jdbcTemplate.update(sqlDays, "Çarşamba", 0);
            jdbcTemplate.update(sqlDays, "Perşembe", 0);
            jdbcTemplate.update(sqlDays, "Cuma", 0);
            jdbcTemplate.update(sqlDays, "Cumartesi", 0);
            jdbcTemplate.update(sqlDays, "Pazar", 0);
        }
    }
}
