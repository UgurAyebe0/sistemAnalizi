package ugurayebe.fun.listener;

import javax.swing.*;

import static ugurayebe.fun.listener.veriables.comboBoxEntries;


public class ComboBoxEntry {
    private String comboBoxName;
    private JComboBox<String> comboBox;
    public ComboBoxEntry(String comboBoxName, JComboBox<String> comboBox) {
        this.comboBoxName = comboBoxName;
        this.comboBox = comboBox;
    }
    public String getComboBoxName() {
        return comboBoxName;
    }
    public JComboBox<String> getComboBox() {
        return comboBox;
    }
    public void setComboBoxSelectedValue(String value) {
        comboBox.setSelectedItem(value);
    }


    // Bu metod, comboBoxName'e göre ilgili ComboBoxEntry örneğini bulur
    public static ComboBoxEntry findComboBoxEntry(String comboBoxName) {
        for (ComboBoxEntry comboBoxEntry : comboBoxEntries) {
            if (comboBoxEntry.getComboBoxName().equals(comboBoxName)) {
                return comboBoxEntry;
            }
        }
        return null;
    }
}
