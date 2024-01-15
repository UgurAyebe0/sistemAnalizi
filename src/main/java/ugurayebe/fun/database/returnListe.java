package ugurayebe.fun.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static ugurayebe.fun.database.config.jdbcTemplate;
import static ugurayebe.fun.listener.veriables.tableModel;

public class returnListe {
    public static List<List<String>> select(String tabloSql) {
        return jdbcTemplate.query(tabloSql, (resultSet, rowNum) -> {
            int columnCount = resultSet.getMetaData().getColumnCount();
            List<String> row = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                row.add(resultSet.getString(i));
            }
            return row;
        });
    }

    public static ArrayList main (String tabloSql){
        ArrayList arrayList = new ArrayList();
        for (List<String> rowData : select(tabloSql)) {
            for (String value : rowData) {
                arrayList.add(value);
            }
        }
        return arrayList;
    }
}
