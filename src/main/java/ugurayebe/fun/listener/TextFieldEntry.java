package ugurayebe.fun.listener;

import javax.swing.*;

import static ugurayebe.fun.listener.veriables.textFieldEntries;

/** TextFieldEntry sınıfı, bir metin alanı ve alan adını içeren öğeleri temsil eder.*/
public class TextFieldEntry {
    private String fieldName;
    private JTextField textField;
    /** TextFieldEntry sınıfının yapıcı metodudur.
     * @param fieldName Metin alanının adını temsil eder.
     * @param textField JTextField türündeki metin alanını temsil eder.*/
    public TextFieldEntry(String fieldName, JTextField textField) {
        this.fieldName = fieldName;
        this.textField = textField;
    }
    /**
     * Metin alanının adını getiren metod.
     * @return Metin alanının adı.
     */
    public String getFieldName() {
        return fieldName;
    }
    /** Metin alanını getiren metod.
     * @return JTextField türündeki metin alanı.*/
    public JTextField getTextField() {
        return textField;
    }
    /** Metin alanının değerini ayarlayan metod.
     * @param value Ayarlanacak metin alanı değeri.*/
    public void setTextFieldValue(String value) {
        textField.setText(value);
    }
    /** Verilen alan adına sahip TextFieldEntry öğesini bulan ve döndüren statik metod.
     * @param fieldName Bulunmak istenen metin alanının adı.
     * @return Eşleşen metin alanı bulunursa TextFieldEntry öğesi, aksi takdirde null.*/
    public static TextFieldEntry findTextFieldEntry(String fieldName) {
        for (TextFieldEntry textFieldEntry : textFieldEntries) {
            if (textFieldEntry.getFieldName().equals(fieldName)) {
                return textFieldEntry;
            }
        }
        return null;
    }
}
