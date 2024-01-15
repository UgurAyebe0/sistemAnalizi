package ugurayebe.fun.controller.frame;

import java.util.ArrayList;
/**
 * FieldOrderHelper sınıfı, alan sıralamasını düzenleyen yardımcı bir sınıftır.
 */
public class getFieldOrder {

    /**
     * Alan sıralamasını düzenleyen metod.
     * @param fieldNames Tablo alan isimlerini içeren ArrayList.
     * @return Düzenlenmiş alan sıralamasını içeren ArrayList.
     */
    public static ArrayList getFieldOrder(ArrayList fieldNames) {

        // Buradan önce Field isimlerini alıyoruz daha sonra comboBox verilerinin ismini alıyoruz.
        // Bunun amacı jComboBox yazısından kurtulmak ve sıralamak.

        ArrayList<String> fieldOrder = new ArrayList<>();

        // Field isimlerini ekleyen döngü
        for (Object fieldName : fieldNames) {
            String field = (String) fieldName;
            if (!field.startsWith("jComboBox")) {
                fieldOrder.add(field);
            }
        }

        // ComboBox verilerinin isimlerini ekleyen döngü
        for (Object fieldName : fieldNames) {
            String field = (String) fieldName;
            if (field.startsWith("jComboBox")) {
                fieldOrder.add(field);
            }
        }

        return fieldOrder;
    }
}
