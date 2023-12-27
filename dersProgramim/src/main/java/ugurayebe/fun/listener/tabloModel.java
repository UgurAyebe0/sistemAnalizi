package ugurayebe.fun.listener;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class tabloModel {
    public static class CustomTableModel extends DefaultTableModel {
        public CustomTableModel(Vector<String> columnNames, int rowCount) {
            super(columnNames, rowCount);
        }
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
