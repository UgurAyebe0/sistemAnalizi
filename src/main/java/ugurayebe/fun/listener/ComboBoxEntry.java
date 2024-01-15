package ugurayebe.fun.listener;

import javax.swing.*;

import static ugurayebe.fun.listener.veriables.comboBoxEntries;

/** ComboBoxEntry sınıfı, bir JComboBox öğesini ve adını içeren öğeleri temsil eder. */
public class ComboBoxEntry {
    private String comboBoxName;
    private JComboBox<String> comboBox;
    /** ComboBoxEntry sınıfının yapıcı metodudur.
     * @param comboBoxName JComboBox'in adını temsil eder.
     * @param comboBox JComboBox türündeki öğeyi temsil eder.*/
    public ComboBoxEntry(String comboBoxName, JComboBox<String> comboBox) {
        this.comboBoxName = comboBoxName;
        this.comboBox = comboBox;
    }
    /** JComboBox'in adını getiren metod.
     * @return JComboBox'in adı */
    public String getComboBoxName() {
        return comboBoxName;
    }
    /** JComboBox'i getiren metod.
     * @return JComboBox türündeki öğe.*/
    public JComboBox<String> getComboBox() {
        return comboBox;
    }
    /*** JComboBox'in seçili değerini ayarlayan metod.
     * @param value Ayarlanacak seçili değer.*/
    public void setComboBoxSelectedValue(String value) {
        comboBox.setSelectedItem(value);
    }

    /** Verilen comboBoxName'e sahip ComboBoxEntry öğesini bulan ve döndüren statik metod.
     * @param comboBoxName Bulunmak istenen JComboBox'in adı.
     * @return Eşleşen JComboBox bulunursa ComboBoxEntry öğesi, aksi takdirde null. */
    public static ComboBoxEntry findComboBoxEntry(String comboBoxName) {
        for (ComboBoxEntry comboBoxEntry : comboBoxEntries) {
            if (comboBoxEntry.getComboBoxName().equals(comboBoxName)) {
                return comboBoxEntry;
            }
        }
        return null;
    }
}
