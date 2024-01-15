package ugurayebe.fun.database.notTable;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class availabilityTeacher {
    public static void main() {
        String sql = "CREATE TABLE IF NOT EXISTS availabilityTeacher ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "Teacher INT NOT NULL,"
                + "Day VARCHAR(255),"
                + "Reason TEXT,"
                + "UNIQUE (Teacher, Day),"
                + "FOREIGN KEY (Teacher) REFERENCES Teacher(id) ON DELETE CASCADE"
                + ")";
        jdbcTemplate.execute(sql);
    }
}
