package ugurayebe.fun.controller.frame.config;

import java.util.ArrayList;

import static ugurayebe.fun.database.config.jdbcTemplate;


/**
 * DataComboBox sınıfı, JComboBox alanlarındaki verileri düzenleyen yardımcı bir sınıftır.
 */
public class dataComboBox {

    /**
     * JComboBox alanlarındaki verileri düzenleyen metod.
     * @param fieldData Kaydedilecek veriyi içeren ArrayList.
     * @param fieldOrder Düzenlenmiş alan sıralamasını içeren ArrayList.
     */
    public static void main(ArrayList fieldData, ArrayList fieldOrder) {

        for (int i = 0; i < fieldOrder.size(); i++) {
            String fieldName = (String) fieldOrder.get(i);
            String value = (String) fieldData.get(i);
            switch (fieldName) {
                case "jComboBoxOp":
                    fieldData.set(i, value.equals("Tam Yetki") ? "1" : "0");
                    break;
                case "jComboBoxBranch":
                    fieldData.set(i, value.equals("Bölünecek") ? "2" : "1");
                    break;
                case "jComboBoxTitle":
                case "jComboBoxClassroom":
                case "jComboBoxEpisode":
                case "jComboBoxLesson":
                case "jComboBoxLessons":
                case "jComboBoxTeacher":
                    handleComboBoxField(fieldName, value, fieldData, i);
                    break;
            }
        }
    }


    /** JComboBox alanlarındaki verileri düzenleyen özel bir metod.
     * @param fieldName JComboBox alanının adını temsil eden bir String.
     * @param value JComboBox'ten alınan değeri temsil eden bir String.
     * @param fieldData Kaydedilecek veriyi içeren ArrayList.
     * @param index ArrayList üzerindeki indeks değerini temsil eden bir tamsayı. */
    private static void handleComboBoxField(String fieldName, String value, ArrayList fieldData, int index) {
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
                whereName = fieldName.equals("jComboBoxEpisode") ? "Name" : "Code";
                if (fieldName.equals("jComboBoxLesson")) {
                    value = (String) fieldData.get(0);}
                break;
            case "jComboBoxLessons":
                columnName = "Lesson";
                whereName = "Name";
                break;
            case "jComboBoxTeacher":
                columnName = "Teacher";
                whereName = "Username";
                break;
            default:
                return;
        }
        String[] parcalar = value.split("/");
        String sql = "SELECT id FROM " + columnName + " WHERE " + whereName + " = '" + parcalar[0] + "'";
        int result = jdbcTemplate.queryForObject(sql, Integer.class);
        fieldData.set(index, result);
    }

}
