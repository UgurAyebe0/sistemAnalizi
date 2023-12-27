package ugurayebe.fun.controller.frame.config;

import java.util.ArrayList;

public class getColumnList {
    public static ArrayList main(ArrayList fieldNames) {
        ArrayList columnList = new ArrayList<>();
        columnList.add("id");
        for (Object field : fieldNames) {
            String fieldName = (String) field;
            if (fieldName.startsWith("jComboBox")) {
                columnList.add(fieldName.substring(9));
            } else {
                columnList.add(fieldName);
            }
        }
        return columnList;
    }
}
