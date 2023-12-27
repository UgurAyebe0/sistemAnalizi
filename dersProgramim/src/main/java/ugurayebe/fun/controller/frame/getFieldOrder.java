package ugurayebe.fun.controller.frame;

import java.util.ArrayList;

public class getFieldOrder {
    public static ArrayList getFieldOrder(ArrayList fieldNames) {

        // Buradan once Field isimlerini alıyoruz daha sonra comboBox verilerinin ismini alıyoruz.
        // Bunun amacı jComboBox yazısından kurtulmak ve sıralamak.

        ArrayList<String> fieldOrder = new ArrayList<>();

        for (Object fieldName : fieldNames) {
            String field = (String) fieldName;
            if (!field.startsWith("jComboBox")) {
                fieldOrder.add(field);
            }
        }

        for (Object fieldName : fieldNames) {
            String field = (String) fieldName;
            if (field.startsWith("jComboBox")) {
                fieldOrder.add(field);
            }
        }

        return fieldOrder;
    }
}
