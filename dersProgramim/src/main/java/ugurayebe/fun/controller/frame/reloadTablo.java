package ugurayebe.fun.controller.frame;

import ugurayebe.fun.controller.frame.config.getColumnList;
import ugurayebe.fun.controller.frame.config.tableComboBox;
import ugurayebe.fun.database.returnListe;

import java.util.ArrayList;
import java.util.Vector;

import static ugurayebe.fun.listener.veriables.*;

public class reloadTablo {
    public static void reload(ArrayList fieldNames, String tabloSql) {

        ArrayList columnList = getColumnList.main(fieldNames);
        ArrayList loadArryList = returnListe.main(tabloSql);

        System.out.println("columnList  " + columnList);
        System.out.println("loadArryList  " + loadArryList);
        tableModel.setRowCount(0);

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
