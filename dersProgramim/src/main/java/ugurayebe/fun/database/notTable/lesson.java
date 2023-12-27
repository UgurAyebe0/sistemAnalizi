package ugurayebe.fun.database.notTable;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class lesson {
    public static void main() {
        String sql = "CREATE TABLE IF NOT EXISTS Lesson ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "Episode INT NOT NULL,"
                + "Classroom INT NOT NULL,"
                + "Code VARCHAR(15) NOT NULL UNIQUE,"
                + "Name VARCHAR(50) NOT NULL,"
                + "Time VARCHAR(255) NOT NULL,"
                + "FOREIGN KEY (Episode) REFERENCES Episode(id) ON DELETE CASCADE,"
                + "FOREIGN KEY (Classroom) REFERENCES Classroom(id) ON DELETE CASCADE,"
                + "UNIQUE (Episode, Code, Name)"
                + ")";
        jdbcTemplate.execute(sql);
    }
}

