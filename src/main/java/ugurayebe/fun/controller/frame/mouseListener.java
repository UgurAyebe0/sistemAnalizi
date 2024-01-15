package ugurayebe.fun.controller.frame;

import ugurayebe.fun.listener.ComboBoxEntry;
import ugurayebe.fun.listener.TextFieldEntry;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static ugurayebe.fun.listener.ComboBoxEntry.findComboBoxEntry;
import static ugurayebe.fun.listener.TextFieldEntry.findTextFieldEntry;
import static ugurayebe.fun.listener.veriables.*;

// Bu sınıf, bir tablo üzerinde fare çift tıklamasını dinleyerek, seçilen satırdaki verileri ilgili alanlara yerleştirir.
public class mouseListener {
    public static void addMouseListenerToTable(ArrayList fieldNames, String frameType) {
        // MouseAdapter sınıfını kullanarak bir fare tıklama olayını dinle
        table.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                // Eğer fare çift tıklaması yapıldıysa
                // Seçilen satırı al
                int selectedRow = table.getSelectedRow();
                // Eğer herhangi bir satır seçilmişse ve satır sayısı geçerliyse
                if (selectedRow != -1 && selectedRow < tableModel.getRowCount() &&
                        !frameType.equals("teacherCourses") && !frameType.equals("episodeCourses")
                        ){
                    // FieldNames listesindeki her bir alan için döngü
                    for (int i = 0; i < fieldNames.size(); i++) {
                        // Alan adını al
                        String data = (String) fieldNames.get(i);
                        // Eğer alan adı "jComboBox" ile başlıyorsa
                        if (data.startsWith("jComboBox")) {
                            // İlgili ComboBoxEntry'yi bul
                            ComboBoxEntry comboBoxEntry = findComboBoxEntry(data);
                            // Seçilen satırdaki değeri ComboBox'a ayarla
                            comboBoxEntry.setComboBoxSelectedValue(String.valueOf(tableModel.getValueAt(selectedRow, i + 1).toString()));
                        }                             // Diğer durumlar için (normal alan adları)
                        else {
                            // İlgili TextFieldEntry'yi bul
                            TextFieldEntry nameTextFieldEntry = findTextFieldEntry(data);
                            // Seçilen satırdaki değeri TextField'a ayarla
                            nameTextFieldEntry.setTextFieldValue(tableModel.getValueAt(selectedRow, i + 1).toString());
                        }
                    }
                }
            }
        }
    });
}
}
