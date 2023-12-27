package ugurayebe.fun.database.get;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class id {
    public static Integer getİD(String frameType, String field, String data) {
        String sqlTeachers = "SELECT id FROM " + frameType + " WHERE " + field + " = ?";
        return jdbcTemplate.queryForObject(sqlTeachers, new Object[]{data}, Integer.class);
    }

    public static boolean getCount(Object frameType, Object field, Object data, Object field2, Object data2) {
        String sql = "SELECT COUNT(*) FROM " + frameType + " WHERE " + field + " = ? AND " + field2 + " = ?";
        return 0 != jdbcTemplate.queryForObject(sql, Integer.class,data,data2);
    }
}
