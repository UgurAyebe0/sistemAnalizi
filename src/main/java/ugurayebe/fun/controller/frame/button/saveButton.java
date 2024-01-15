package ugurayebe.fun.controller.frame.button;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import ugurayebe.fun.controller.frame.config.dataComboBox;
import ugurayebe.fun.listener.ComboBoxEntry;
import ugurayebe.fun.listener.TextFieldEntry;
import ugurayebe.fun.listener.showMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static ugurayebe.fun.controller.frame.getFieldOrder.getFieldOrder;
import static ugurayebe.fun.controller.frame.reloadTablo.reload;
import static ugurayebe.fun.controller.frame.validData.valid;
import static ugurayebe.fun.database.config.jdbcTemplate;
import static ugurayebe.fun.listener.veriables.*;


public class saveButton {

    /**
     * Veri kaydetme işlemlerini gerçekleştiren JButton'i oluşturan metod.
     *
     * @param fieldNames Tablo alan isimlerini içeren ArrayList.
     * @param frameType  Pencere türünü temsil eden bir String.
     * @param tabloSql   Verilerin alındığı SQL sorgusunu içeren bir String.
     * @return Veri kaydetme işlemlerini gerçekleştiren JButton.
     */
    public static JButton saveButton(ArrayList fieldNames, String frameType, String tabloSql) {
        JButton button = new JButton("Kayıt Et");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                    // Özel durumlar için kontrol ekleyebilirsiniz
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
                // Uyarı mesajı olacağı için mesajı önce temizliyoruz..
                alertMesage = " ";
                // Kayıt etme işlemini gerçekleştiren metod
                saveData(fieldData, fieldNames, frameType, tabloSql);
            }
        });
        return button;
    }

    private static void saveData(ArrayList fieldData, ArrayList fieldNames, String frameType, String tabloSql) {

        // İlk önce Arrylistimizi düzenliyoruz ve sıralıyoruz (önce field sonra jComboBox)
        ArrayList fieldOrder = getFieldOrder(fieldNames);

        // Buradan kontroller yapıyoruz, Eklenmesinde sakıncalar varmı diye bakıyoruz..
        if (valid(fieldData, fieldOrder, frameType, 0, false)) {

            // Arrylist içindeki verileri düzenleyeceğiz mesela Tam Yetki comboBox var ama biz bunu
            // Veritabanında 1 olarak kayıt edeceğiz bunları düzenleyecek..
            dataComboBox.main(fieldData, fieldOrder);

            // Kaydı tamamlıyoruz ve sql sorgusunu göndermek için hazırladık verileri...
            executeInsertQuery(fieldData, fieldOrder, frameType);

            // Tabloyu yeniliyoruz..
            reload(fieldNames, tabloSql);
        }
    }

    private static void executeInsertQuery(ArrayList fieldData, ArrayList fieldOrder, String frameType) {
        try {
            // Sql sorgusu oluşturma ve çalıştırma
            String sql = generateInsertQuery(fieldOrder, frameType);
            jdbcTemplate.update(sql, fieldData.toArray());
            showMessage.main("Tabloya veri eklendi.");
        } catch (DuplicateKeyException e) {
            showMessage.main("Bu bilgilere ait zaten bir kayıt var.");
        } catch (DataIntegrityViolationException e) {
            // Hata mesajını burada
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



    /**
     * GenerateInsertQuery sınıfı, veri ekleme sorgusunu oluşturan yardımcı bir sınıftır.
     Veri ekleme sorgusunu oluşturan metod.
     * @param fieldOrder Düzenlenmiş alan sıralamasını içeren ArrayList.
     * @param process İlgili işlemi temsil eden bir String.
     * @return Oluşturulan veri ekleme sorgusu.
     */
    private static String generateInsertQuery(ArrayList<String> fieldOrder, String process) {
        // Burada ufak bir kısıtlama yapıyoruz..
        if (process.equals("academic_program_episode") || process.equals("academic_program_teacher")) {
            process = "academic_program";
        }
        // Sql sorgusunun ilk başını belirliyoruzz...
        StringBuilder sqlQuery = new StringBuilder("INSERT INTO " + process + "(");
        // Eğer bir Field ise düz ekle ve sonuna (",") koy mesela TC, gibi..
        for (String fieldName : fieldOrder) {
            if (!fieldName.startsWith("jComboBox")) {
                sqlQuery.append(fieldName).append(",");
            }
        }
        // Eğer jComboBoxName ise ilk 9 harfi yok et ve Name olarak yazdır...
        for (String fieldName : fieldOrder) {
            if (fieldName.startsWith("jComboBox")) {
                sqlQuery.append(fieldName.substring(9)).append(",");}}
        // Oluşan son verideki son harfi sil
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);
        // Oluşan veriye bunu ekle
        sqlQuery.append(") VALUES(");
        // Fieldorder kadar ?, koy ve döndür
        for (int i = 0; i < fieldOrder.size(); i++) {
            sqlQuery.append("?,");
        }
        // Son harfi sil virgül olacağı için
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);
        // Parantezi kapat.
        sqlQuery.append(")");
        return sqlQuery.toString();
    }

}
