package ugurayebe.fun.controller.frame.config;


import static ugurayebe.fun.database.config.jdbcTemplate;

public class tableComboBox {
    public static String main(String fieldName, Object value) {
        String id = (String) value;
        switch (fieldName) {
            case "Op":
                return (teacherPermComboBox(id));
            case "Title":
                return (getColumnName("Title", id, "Name"));
            case "Teacher":
                return (getColumnName("Teacher", id, "Username"));
            case "Episode":
                return (getColumnName("Episode", id, "Name"));
            case "Lesson":
                return (getColumnName("Lesson", id, "Name"));
            case "Classroom":
                return (getColumnName("Classroom", id, "Type"));
            default:
                return id;
        }
    }
    public static String getColumnName(String tableName, Object id, String dataColumnName) {
        String sql = "SELECT " + dataColumnName + " FROM " + tableName + " WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
    }
    public static String teacherPermComboBox(String id) {
        if (id.equals("1")) {
            return "Tam Yetki";
        } else return "Kısıtlı Yetki";
    }
}
