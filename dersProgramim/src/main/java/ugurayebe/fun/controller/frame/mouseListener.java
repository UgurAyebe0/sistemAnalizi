package ugurayebe.fun.controller.frame;

import ugurayebe.fun.listener.ComboBoxEntry;
import ugurayebe.fun.listener.TextFieldEntry;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static ugurayebe.fun.listener.ComboBoxEntry.findComboBoxEntry;
import static ugurayebe.fun.listener.TextFieldEntry.findTextFieldEntry;
import static ugurayebe.fun.listener.veriables.*;

public class mouseListener {
    public static void addMouseListenerToTable(ArrayList fieldNames) {
    table.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {

                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1 && selectedRow < tableModel.getRowCount()) {

                    for (int i = 0; i < fieldNames.size(); i++) {
                        String data = (String) fieldNames.get(i);
                        if (data.startsWith("jComboBox")) {
                            ComboBoxEntry comboBoxEntry = findComboBoxEntry(data);
                            comboBoxEntry.setComboBoxSelectedValue(String.valueOf(tableModel.getValueAt(selectedRow, i + 1).toString()));
                        } else {
                            TextFieldEntry nameTextFieldEntry = findTextFieldEntry(data);
                            nameTextFieldEntry.setTextFieldValue(tableModel.getValueAt(selectedRow, i + 1).toString());
                        }
                    }
                }
            }
        }
    });
}
}
