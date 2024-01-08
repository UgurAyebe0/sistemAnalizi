package ugurayebe.fun.controller.frame.button;

import ugurayebe.fun.listener.showMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static ugurayebe.fun.controller.frame.reloadTablo.reload;
import static ugurayebe.fun.database.config.jdbcTemplate;
import static ugurayebe.fun.listener.veriables.*;


public class removeButton {
    public static JButton removeButton(ArrayList fieldNames,String process, String tabloSql) {
        JButton button = new JButton("Veriyi Sil");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                String sql = "DELETE FROM " + deneme(process) + " WHERE id = ?";
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {

                    int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                    jdbcTemplate.update(sql, id);
                    showMessage.main("Veri tablodan silindi.");
                    reload(fieldNames,tabloSql);
                } else {
                    showMessage.main("Lütfen tablodan bir veri seçin.");
                }
            }
        });
        return button;
    }
    public static String deneme (String Process){

        if (Process.equals("academic_program_episode") || Process.equals("academic_program_teacher")){
            Process = "academic_program" ;
        }
        return Process;
    }
}
