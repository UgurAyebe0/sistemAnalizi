package ugurayebe.fun.listener;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class tabloModel {
    /**
     * CustomTableModel sınıfı, özel tablo modellerini temsil eder ve DefaultTableModel'i genişletir.
     */
    public static class CustomTableModel extends DefaultTableModel {
        /**
         * CustomTableModel sınıfının yapıcı metodudur.
         * @param columnNames Tablo sütun isimlerini içeren vektör.
         * @param rowCount Satır sayısını temsil eden bir tamsayı.
         */
        public CustomTableModel(Vector<String> columnNames, int rowCount) {
            super(columnNames, rowCount);
        }
        /**
         * Belirtilen hücrenin düzenlenebilir olup olmadığını belirleyen metod.
         * @param row Hücrenin bulunduğu satır indeksi.
         * @param column Hücrenin bulunduğu sütun indeksi.
         * @return Hücrenin düzenlenebilir olup olmadığını gösteren boolean değer.
         */
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
