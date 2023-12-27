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
    public static JButton updateButton(ArrayList fieldNames, String frameType, String tabloSql) {
        JButton button = new JButton("Güncelle");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {

                    Object memberİd = tableModel.getValueAt(selectedRow, 0).toString();


                    ArrayList fieldData = new ArrayList<>();
                    for (TextFieldEntry textFieldEntry : textFieldEntries) {
                        String value = textFieldEntry.getTextField().getText();
                        fieldData.add(value);
                    }
                    for (ComboBoxEntry comboBoxEntry : comboBoxEntries) {
                        String value = comboBoxEntry.getComboBox().getSelectedItem().toString();
                        fieldData.add(value);
                    }
                    saveData(fieldData, fieldNames, frameType, memberİd, tabloSql);
                } else {
                    showMessage.main("Lütfen güncelenecek veriyi seciniz.");
                }
            }
        });
        return button;
    }

    private static void saveData(ArrayList fieldData, ArrayList fieldNames, String frameType, Object memberİd, String tabloSql) {
        ArrayList<String> fieldOrder = getFieldOrder(fieldNames);
        if (valid(fieldData, fieldOrder, frameType, memberİd, true)) {
            if (showAlerty(fieldData, fieldNames)) {
                dataComboBox.main(fieldData, fieldOrder);
                executeInsertQuery(fieldData, fieldOrder, frameType, memberİd);
                reload(fieldNames, tabloSql);
            }
        }


    }

    private static boolean showAlerty(ArrayList<Object> fieldData, ArrayList fieldNames) {

        int selectedRow = table.getSelectedRow();
        String alertMesage = "Yapılacak değişiklikleri onaylıyor musunuz ?\n\n Asıl veriler:";
        int sayaç = 0;

        for (int i = 0; i < fieldNames.size(); i++) {
            String field = (String) fieldNames.get(i);
            if (field.startsWith("jCom")) {
                alertMesage = alertMesage + "\n" + field.substring(9) + ": " + tableModel.getValueAt(selectedRow, i + 1).toString();
                sayaç++;

            } else {
                alertMesage = alertMesage + "\n" + field + ": " + tableModel.getValueAt(selectedRow, i + 1).toString();
            }
        }
        alertMesage = alertMesage + "\n\n Güncellenecek veriler:";
        int c = sayaç;
        for (int i = 0; i < fieldNames.size(); i++) {
            String field = (String) fieldNames.get(i);
            if (field.startsWith("jC")) {
                alertMesage = alertMesage + "\n" + field.substring(9) + ": " + fieldData.get(fieldNames.size() - sayaç);
                sayaç--;
            } else {
                alertMesage = alertMesage + "\n" + field + ": " + fieldData.get(i - c);
            }
        }
        int result = JOptionPane.showConfirmDialog(null, alertMesage, "Onay", JOptionPane.OK_CANCEL_OPTION);

        boolean confirmed = false;
        if (result == JOptionPane.OK_OPTION) {
            confirmed = true;
        }
        return true;
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
        StringBuilder sqlQuery = new StringBuilder("UPDATE " + frameType + " SET ");
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
        sqlQuery.deleteCharAt(sqlQuery.length() - 2);
        sqlQuery.append("WHERE id = ?");
        return sqlQuery.toString();
    }
}