package ugurayebe.fun.controller.frame;

import ugurayebe.fun.controller.frame.config.getColumnList;
import ugurayebe.fun.controller.frame.config.tableComboBox;
import ugurayebe.fun.database.returnListe;

import java.util.ArrayList;
import java.util.Vector;

import static ugurayebe.fun.listener.veriables.*;

public class reloadTablo {
    /**
     * Yeniden yükleme işlemlerini gerçekleştiren bir yardımcı sınıf.
     */
    public static void reload(ArrayList fieldNames, String tabloSql) {

        // Sütun isimlerini al
        ArrayList columnList = getColumnList.main(fieldNames);
        // SQL tablosundan veri al
        ArrayList loadArryList = returnListe.main(tabloSql);
        // Tablo modelinin satır sayısını sıfırla
        tableModel.setRowCount(0);
        // Verileri tabloya ekle
        for (int i = 0; i < loadArryList.size(); i = i + columnList.size()) {
            Vector<Object> row = new Vector<>();
            for (int j = 0; j < columnList.size(); j++) {
                String field = (String) columnList.get(j);
                row.add(tableComboBox.main(field, loadArryList.get(j + i)));
            }
            tableModel.addRow(row);
        }
    }
}
