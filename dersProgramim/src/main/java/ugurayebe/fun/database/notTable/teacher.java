package ugurayebe.fun.database.notTable;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class teacher {
    public static void main() {
        String sql = "CREATE TABLE IF NOT EXISTS Teacher ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "Title INT NOT NULL,"
                + "Op BOOLEAN,"
                + "TC VARCHAR(15) NOT NULL UNIQUE,"
                + "PhoneNumber VARCHAR(15) NOT NULL UNIQUE,"
                + "Name VARCHAR(255) NOT NULL,"
                + "Surname VARCHAR(255) NOT NULL,"
                + "Username VARCHAR(255) NOT NULL UNIQUE,"
                + "Password VARCHAR(255) NOT NULL, "
                + "FOREIGN KEY (Title) REFERENCES Title(id) ON DELETE CASCADE"
                + ")";
        jdbcTemplate.execute(sql);
    }
}
