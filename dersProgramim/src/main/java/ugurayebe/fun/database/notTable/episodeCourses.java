package ugurayebe.fun.database.notTable;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class episodeCourses {
    public static void main() {
        String sql = "CREATE TABLE IF NOT EXISTS episodeCourses("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "Episode INT NOT NULL,"
                + "Code VARCHAR(15) NOT NULL,"
                + "Lesson INT NOT NULL,"
                + "FOREIGN KEY (Episode) REFERENCES Episode(id) ON DELETE CASCADE,"
                + "FOREIGN KEY (Lesson) REFERENCES Lesson(id) ON DELETE CASCADE,"
                + "UNIQUE (Episode, Lesson)"
                + ")";
        jdbcTemplate.execute(sql);
    }
}