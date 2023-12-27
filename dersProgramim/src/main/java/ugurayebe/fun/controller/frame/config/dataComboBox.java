package ugurayebe.fun.controller.frame.config;

import java.util.ArrayList;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class dataComboBox {
    public static void main(ArrayList fieldData, ArrayList fieldOrder) {

        System.out.println("fieldData " + fieldData);
        System.out.println("fieldOrder " + fieldOrder);


        for (int i = 0; i < fieldOrder.size(); i++) {
            String fieldName = (String) fieldOrder.get(i);
            String value = (String) fieldData.get(i);
            switch (fieldName) {
                case "jComboBoxOp":
                    fieldData.set(i, value.equals("Tam Yetki") ? "1" : "0");
                    break;
                case "jComboBoxTitle":
                case "jComboBoxClassroom":
                case "jComboBoxEpisode":
                case "jComboBoxLesson":
                case "jComboBoxTeacher":
                    handleComboBoxField(fieldName, value, fieldData, i);
                    break;
            }
        }
    }

    private static void handleComboBoxField(String fieldName, String value, ArrayList<Object> fieldData, int index) {
        String columnName;
        String whereName;

        switch (fieldName) {
            case "jComboBoxTitle":
            case "jComboBoxClassroom":
                columnName = fieldName.equals("jComboBoxTitle") ? "Title" : "Classroom";
                whereName = fieldName.equals("jComboBoxTitle") ? "Name" : "Type";
                break;
            case "jComboBoxEpisode":
            case "jComboBoxLesson":
                columnName = fieldName.equals("jComboBoxEpisode") ? "Episode" : "Lesson";
                whereName = "Name";
                break;

            case "jComboBoxTeacher":
                columnName = "Teacher";
                whereName = "Username";
                break;

            default:
                return;
        }

        String sql = "SELECT id FROM " + columnName + " WHERE " + whereName + " = '" + value + "'";
        int result = jdbcTemplate.queryForObject(sql, Integer.class);
        fieldData.set(index, result);
    }

}
