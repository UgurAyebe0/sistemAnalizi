package ugurayebe.fun.controller.frame.button;


import org.springframework.dao.EmptyResultDataAccessException;
import ugurayebe.fun.controller.frame.createFrame;
import ugurayebe.fun.listener.showMessage;
import ugurayebe.fun.view.opMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static ugurayebe.fun.database.config.jdbcTemplate;
import static ugurayebe.fun.listener.TextFieldEntry.findTextFieldEntry;
import static ugurayebe.fun.listener.veriables.*;

public class loginButton {

    public static JButton loginButton() {

        JButton button = new JButton("Giriş Yap");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Field verilerdeki bilgileri alıyoruz.

                String username = findTextFieldEntry("Username").getTextField().getText();
                String password = findTextFieldEntry("Password").getTextField().getText();

                // Boş değilse...
                if (!username.isEmpty() || !password.isEmpty()) {
                    // Burada böyle username ve passworld varmı diye kontrol sağlıyruz.
                    if (verify(username, password)) {
                        frame.dispose();

                        switch (getOpValueByUsername(username)) {
                            case 0:

                                // Pencere oluşturuyoruz. ve öğretmen penceresinde hiçbir eylem yapamayacak. CreateFrame de belirttim.

                                String usernameSql = "SELECT id FROM Teacher Where username = ?";
                                int id = jdbcTemplate.queryForObject(usernameSql, Integer.class, username);
                                System.out.println(id);
                                ArrayList fieldName = new ArrayList();
                                fieldName.add("jComboBoxEpisode");
                                fieldName.add("jComboBoxLessons");
                                fieldName.add("jComboBoxTeacher");
                                fieldName.add("jComboBoxDay");
                                fieldName.add("jComboBoxLessonTime");
                                fieldName.add("jComboBoxClassroom");
                                fieldName.add("Lesson_Code");
                                fieldName.add("Classroom_Number");

                                String tabloSql = "Select id,Episode,Lessons,Teacher,Day,LessonTime,Classroom,Lesson_Code,Classroom_Number" +
                                        " from academic_program Where Teacher = '"+ id + "' ORDER BY Episode";
                                createFrame.main(username + " - Öğretmen Ders Programı", 1000, 600, fieldName, 4,
                                        "loginUsername", tabloSql );
                                break;
                            case 1:
                                // tam yetki tüm konfigurasyonları yapabileceği menüye atıyorum.
                                opMenu.opPermission();
                                break;
                        }
                    } else {
                        showMessage.main("Kullanıcı adı veya şifreniz hatalı.");
                    }
                } else {
                    showMessage.main("Lütfen boş bırakmayın.");
                }


            }
        });
        return button;
    }

    // Eğer username ve password 0 tane varsa haliyle kullanıcı olmaz.
    // o yüzden böyle bir login sistemi hazırladım.
    public static boolean verify(String username, String password) {
        String sql = "SELECT COUNT(*) FROM Teacher WHERE Username = ? AND Password = ?";
        return 0 != jdbcTemplate.queryForObject(sql, Integer.class, username, password);
    }


    // Kullanıcı op mu öğretmenmi bunu alıyoruz..
    public static int getOpValueByUsername(String username) {
        String sql = "SELECT Op FROM Teacher WHERE Username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, username);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
}

