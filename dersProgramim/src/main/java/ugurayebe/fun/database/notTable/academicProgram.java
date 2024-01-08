package ugurayebe.fun.database.notTable;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class academicProgram {
    public static void main() {
        String sql = "CREATE TABLE IF NOT EXISTS Academic_program ("
                + "Id INT AUTO_INCREMENT PRIMARY KEY,"
                + "Episode INT NOT NULL,"
                + "Lessons INT NOT NULL,"
                + "Lesson_Code VARCHAR(255) NOT NULL,"
                + "Teacher INT NOT NULL,"
                + "Classroom INT NOT NULL,"
                + "Classroom_Number INT NOT NULL,"
                + "Day VARCHAR(255) NOT NULL,"
                + "LessonTime VARCHAR(255) NOT NULL,"
                + "FOREIGN KEY (Lesson) REFERENCES Lesson(id) ON DELETE CASCADE,"
                + "FOREIGN KEY (Episode) REFERENCES Episode(id) ON DELETE CASCADE,"
                + "FOREIGN KEY (Teacher) REFERENCES Teacher(id) ON DELETE CASCADE,"
                + "FOREIGN KEY (Classroom) REFERENCES Classroom(id) ON DELETE CASCADE"
                + ")";
        jdbcTemplate.execute(sql);
    }
}
