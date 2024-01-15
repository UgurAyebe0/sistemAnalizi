package ugurayebe.fun.controller.frame.button;


import org.springframework.dao.DuplicateKeyException;
import ugurayebe.fun.controller.frame.config.dataComboBox;
import ugurayebe.fun.listener.ComboBoxEntry;
import ugurayebe.fun.listener.TextFieldEntry;
import ugurayebe.fun.listener.showMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import static ugurayebe.fun.controller.frame.getFieldOrder.getFieldOrder;
import static ugurayebe.fun.controller.frame.reloadTablo.reload;
import static ugurayebe.fun.controller.frame.validData.valid;
import static ugurayebe.fun.database.config.jdbcTemplate;
import static ugurayebe.fun.listener.veriables.*;


public class updateButton {

    /**
     * Veri kaydetme işlemlerini gerçekleştiren JButton'i oluşturan metod.
     *
     * @param fieldNames Tablo alan isimlerini içeren ArrayList.
     * @param frameType  Pencere türünü temsil eden bir String.
     * @param tabloSql   Verilerin alındığı SQL sorgusunu içeren bir String.
     * @return Veri güncelleme işlemlerini gerçekleştiren JButton.
     */
    public static JButton updateButton(ArrayList fieldNames, String frameType, String tabloSql) {
        JButton button = new JButton("Güncelle");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Buradan secilen row kayıt ediyoruzz..
                int selectedRow = table.getSelectedRow();
                // Eğer secildiyse...
                if (selectedRow != -1) {
                    // İd değerini alıyoruz..
                    Object memberİd = tableModel.getValueAt(selectedRow, 0).toString();
                    // Burada field içindeki verileri fieldData Kayıt ediyoruz.
                    ArrayList fieldData = new ArrayList<>();
                    // TextFieldEntry listesini döngüye alarak text alanlarındaki verileri al
                    for (TextFieldEntry textFieldEntry : textFieldEntries) {
                        String value = textFieldEntry.getTextField().getText();
                        fieldData.add(value);
                    }
                    // ComboBoxEntry listesini döngüye alarak combo box alanlarındaki verileri al
                    for (ComboBoxEntry comboBoxEntry : comboBoxEntries) {
                        String value = comboBoxEntry.getComboBox().getSelectedItem().toString();
                        fieldData.add(value);
                    }
                    alertMesage = " ";
                    updateData(fieldData, fieldNames, frameType, memberİd, tabloSql);
                } else {
                    showMessage.main("Lütfen güncelenecek veriyi seciniz.");
                }
            }
        });
        return button;
    }

    private static void updateData(ArrayList fieldData, ArrayList fieldNames, String frameType, Object memberİd, String tabloSql) {
        ArrayList<String> fieldOrder = getFieldOrder(fieldNames);
        if (valid(fieldData, fieldOrder, frameType, memberİd, true)) {
            if (showAlerty(fieldData, fieldNames, frameType)) {
                dataComboBox.main(fieldData, fieldOrder);
                executeInsertQuery(fieldData, fieldOrder, frameType, memberİd);
                reload(fieldNames, tabloSql);
            }
        }
    }


    private static boolean showAlerty(ArrayList<Object> fieldData, ArrayList fieldNames, String frameType) {
        // Burada güncelleme ile ilgili mesaj yayınlanması içindir..
        // Mesajın ilk kısmını belirliyorum...
        alertMesage = alertMesage + "Yapılacak değişiklikleri onaylıyor musunuz ?\n\n Asıl veriler:";
        int sayaç = 0;

        // Asıl tablodan verileri cekerek belirtiyorum.
        int selectedRow = table.getSelectedRow();
        for (int i = 0; i < fieldNames.size(); i++) {
            String field = (String) fieldNames.get(i);
            if (field.startsWith("jCom")) {
                alertMesage = alertMesage + "\n" + field.substring(9) + ": " + tableModel.getValueAt(selectedRow, i + 1).toString();
                sayaç++;
            } else {
                alertMesage = alertMesage + "\n" + field + ": " + tableModel.getValueAt(selectedRow, i + 1).toString();
            }
        }
        // Güncellenecek verileri listeliyorum..
        alertMesage = alertMesage + "\n\n Güncellenecek veriler:";
        int c = sayaç;
        for (int i = 0; i < fieldNames.size(); i++) {
            String field = (String) fieldNames.get(i);
            if (field.startsWith("jC")) {
                alertMesage = alertMesage + "\n" + field.substring(9) + ": " + fieldData.get(fieldNames.size() - sayaç);
                sayaç--;
            } else {
                if (!frameType.equals("Lesson")) {
                    alertMesage = alertMesage + "\n" + field + ": " + fieldData.get(i - c);
                } else {
                    alertMesage = alertMesage + "\n" + field + ": " + fieldData.get(i);}}}
        // Ve bir uyarı penceresi acıyorum uyarı penceresine göre durum bildiriyorum..
        int result = JOptionPane.showConfirmDialog(null, alertMesage, "Onay", JOptionPane.OK_CANCEL_OPTION);
        boolean confirmed = false;
        if (result == JOptionPane.OK_OPTION) {
            confirmed = true;}
        return confirmed;
    }


    private static void executeInsertQuery(ArrayList fieldData, ArrayList<String> fieldOrder, String frameType, Object memberId) {
        try {
            String sql = generateInsertQuery(fieldOrder, frameType);
            fieldData.add(memberId);
            jdbcTemplate.update(sql, fieldData.toArray());
            showMessage.main("Veri başarıyla güncellendi.");
        } catch (DuplicateKeyException e) {
            showMessage.main("Bu bilgilere ait zaten bir kayıt var.");
        }
    }

    private static String generateInsertQuery(ArrayList<String> fieldOrder, String frameType) {

        // Ufak kısıtlamalarımız..
        if (frameType.equals("academic_program_episode") || frameType.equals("academic_program_teacher")){
            frameType = "academic_program" ;
        }

        // Sql sorgusunun ilk başını belirliyoruzz...
        StringBuilder sqlQuery = new StringBuilder("UPDATE " + frameType + " SET ");

        // FieldNamesini ve = ? ardından bunu koyması beliryiyoruz..
        for (String fieldName : fieldOrder) {
            if (!fieldName.startsWith("jComboBox")) {
                sqlQuery.append(fieldName).append(" = ?, ");
            }
        }
        for (String fieldName : fieldOrder) {
            if (fieldName.startsWith("jComboBox")) {
                sqlQuery.append(fieldName.substring(9)).append(" = ?, ");
            }
        }

        //  Daha sonra son 2 verisini silip Boşluk ve virgülü..
        sqlQuery.deleteCharAt(sqlQuery.length() - 2);

        // Nereden düzelteceğini gösteriyoruz...
        sqlQuery.append("WHERE id = ?");

        return sqlQuery.toString();
    }
}