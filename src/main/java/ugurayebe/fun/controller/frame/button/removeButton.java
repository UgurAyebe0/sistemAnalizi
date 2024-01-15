package ugurayebe.fun.controller.frame.button;

import ugurayebe.fun.listener.showMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static ugurayebe.fun.controller.frame.reloadTablo.reload;
import static ugurayebe.fun.database.config.jdbcTemplate;
import static ugurayebe.fun.listener.veriables.*;

/**
 * removeButton sınıfı, veriyi silme işlemlerini gerçekleştiren bir JButton oluşturur.
 */
public class removeButton {
    /**
     * Veriyi silme işlemlerini gerçekleştiren JButton'i oluşturan metod.
     * @param fieldNames Tablo alan isimlerini içeren ArrayList.
     * @param process İlgili işlemi temsil eden bir String.
     * @param tabloSql Verilerin alındığı SQL sorgusunu içeren bir String.
     * @return Veriyi silme işlemlerini gerçekleştiren JButton.
     */
    public static JButton removeButton(ArrayList fieldNames, String process, String tabloSql) {
        JButton button = new JButton("Veriyi Sil");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sql Sorgumuz
                String sql = "DELETE FROM " + tabloName(process) + " WHERE id = ?";

                // Secilen stün alıyoruz
                int selectedRow = table.getSelectedRow();

                // Eğer Seçildiyse...
                if (selectedRow != -1) {
                    // Stün 0. indexi İd olduğu için 0. İndexi alıyoruz..
                    int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                    //Sql sorgumuzu çalıştırdık..
                    jdbcTemplate.update(sql, id);
                    showMessage.main("Veri tablodan silindi.");
                    reload(fieldNames, tabloSql);
                } else {
                    showMessage.main("Lütfen tablodan bir veri seçin.");
                }
            }
        });
        return button;
    }

    /**
     * İşlem adına göre tablo adını döndüren özel bir metod.
     * @param Process İşlem adını temsil eden bir String.
     * @return İşlem adına göre belirlenen tablo adı.
     */
    private static String tabloName(String Process) {

        if (Process.equals("academic_program_episode") || Process.equals("academic_program_teacher")) {
            Process = "academic_program";
        }
        return Process;
    }
}