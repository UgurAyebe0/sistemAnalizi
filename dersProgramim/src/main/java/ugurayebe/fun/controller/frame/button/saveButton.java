package ugurayebe.fun.controller.frame.button;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ugurayebe.fun.controller.frame.config.dataComboBox;
import ugurayebe.fun.listener.ComboBoxEntry;
import ugurayebe.fun.listener.TextFieldEntry;
import ugurayebe.fun.listener.showMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ugurayebe.fun.controller.frame.button.updateButton.alertMesage;
import static ugurayebe.fun.controller.frame.getFieldOrder.getFieldOrder;
import static ugurayebe.fun.controller.frame.reloadTablo.reload;
import static ugurayebe.fun.controller.frame.validData.valid;
import static ugurayebe.fun.database.config.jdbcTemplate;
import static ugurayebe.fun.listener.veriables.*;


public class saveButton {

    public static JButton saveButton(ArrayList fieldNames, String frameType, String tabloSql) {
        JButton button = new JButton("Kayıt Et");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Burada field içindeki verileri fieldData Kayıt ediyoruz.
                ArrayList fieldData = new ArrayList<>();


                for (TextFieldEntry textFieldEntry : textFieldEntries) {
                    String value = textFieldEntry.getTextField().getText();
                    fieldData.add(value);
                }

                for (ComboBoxEntry comboBoxEntry : comboBoxEntries) {
                    String value = comboBoxEntry.getComboBox().getSelectedItem().toString();

                    System.out.println("value " + value);

                    if ((frameType.equals("teacherCourses") || frameType.equals("episodeCourses")) && value.startsWith("(")) {
                        String[] slot = value.split(" ", 2);
                        System.out.println(fieldData.get(0));
                        fieldData.add(fieldData.get(0));
                        fieldData.set(0, slot[0].substring(1, slot[0].length() - 1));
                        fieldData.add(slot[1]);
                        continue;
                    }


                    fieldData.add(value);
                }
                saveData(fieldData, fieldNames, frameType, tabloSql);
            }
        });
        return button;
    }

    private static void saveData(ArrayList fieldData, ArrayList fieldNames, String frameType, String tabloSql) {
        ArrayList fieldOrder = getFieldOrder(fieldNames);
        if (valid(fieldData, fieldOrder, frameType, 0, false)) {
            alertMesage = " ";
            dataComboBox.main(fieldData, fieldOrder);
            executeInsertQuery(fieldData, fieldOrder, frameType);
            reload(fieldNames, tabloSql);
        }
    }

    private static void executeInsertQuery(ArrayList fieldData, ArrayList fieldOrder, String frameType) {
        try {
            String sql = generateInsertQuery(fieldOrder, frameType);
            System.out.println("SQL Execute: " + sql);
            jdbcTemplate.update(sql, fieldData.toArray());
            showMessage.main("Tabloya veri eklendi.");
        } catch (DuplicateKeyException e) {
            showMessage.main("Bu bilgilere ait zaten bir kayıt var.");
        } catch (DataIntegrityViolationException e) {
            // Hata mesajını buraya ekleyin
            String errorMessage = e.getMessage();
            String tableName = parseTableNameFromErrorMessage(errorMessage);
            showMessage.main("Veritabanı hatası: " + tableName);
        }
    }

    private static String parseTableNameFromErrorMessage(String errorMessage) {
        // Hata mesajında "`dersprogrami`.`lesson`" ifadesini arayarak tablo adını çıkartıyoruz
        int startIndexOfTable = errorMessage.indexOf("`");
        int endIndexOfTable = errorMessage.lastIndexOf("`");

        if (startIndexOfTable != -1 && endIndexOfTable != -1 && endIndexOfTable > startIndexOfTable) {
            return errorMessage.substring(startIndexOfTable + 1, endIndexOfTable);
        }

        // Eğer "`" ifadeleri bulunamazsa veya sıraları doğru değilse, varsayılan olarak "unknown_table" döndürüyoruz.
        return "unknown_table";
    }


    private static String generateInsertQuery(ArrayList<String> fieldOrder, String process) {

        if (process.equals("academic_program_episode") || process.equals("academic_program_teacher")){
            process = "academic_program" ;
        }

        StringBuilder sqlQuery = new StringBuilder("INSERT INTO " + process + "(");
        for (String fieldName : fieldOrder) {
            if (!fieldName.startsWith("jComboBox")) {
                sqlQuery.append(fieldName).append(",");
            }
        }
        for (String fieldName : fieldOrder) {
            if (fieldName.startsWith("jComboBox")) {
                sqlQuery.append(fieldName.substring(9)).append(",");
            }
        }
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);
        sqlQuery.append(") VALUES(");
        for (int i = 0; i < fieldOrder.size(); i++) {
            sqlQuery.append("?,");
        }
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);
        sqlQuery.append(")");
        return sqlQuery.toString();
    }

}
