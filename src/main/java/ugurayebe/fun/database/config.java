package ugurayebe.fun.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class config {
    public static DriverManagerDataSource dataSource;
    public static JdbcTemplate jdbcTemplate;

    static {
        // DataSource konfigürasyonu
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/dersProgrami");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
