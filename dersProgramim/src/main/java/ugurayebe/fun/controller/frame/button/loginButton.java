package ugurayebe.fun.controller.frame.button;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import ugurayebe.fun.listener.showMessage;
import ugurayebe.fun.view.opMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ugurayebe.fun.database.config.jdbcTemplate;
import static ugurayebe.fun.listener.veriables.*;

public class loginButton {

    public static JButton loginButton() {

        JButton button = new JButton("Giriş Yap");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Field verilerdeki bilgileri alıyoruz.

//                String username = findTextFieldEntry("Username").getTextField().getText();
//                String password = findTextFieldEntry("Password").getTextField().getText();

                    String username = "enginucar";
                    String password = "enginucar";

                // Boş değilse...
                if (!username.isEmpty() || !password.isEmpty()) {
                    // Burada böyle username ve passworld varmı diye kontrol sağlıyruz.
                    if (verify(username, password)) {
                        frame.dispose();
                        switch (getOpValueByUsername(username)) {
                            case 0:
                                frame.dispose();
//                            usersFrame.main("Merhaba, " + username, 800, 600,
//                                    new String[]{"jComboBoxEpisode", "jComboBoxLesson", "jComboBoxClassroom", "jComboBoxDay", "jComboBoxLessonTime"},
//                                    "academic_program", "select * from academic_program where teachers ='" + id + "'");
                                break;
                            case 1:
                                opMenu.opPermission();
                                break;
                        }
                    } else {
                        showMessage.main("Kullanıcı adı veya şifreniz hatalı.");
                    }
                }else{
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

    public static int getOpValueByUsername(String username) {
        String sql = "SELECT Op FROM Teacher WHERE Username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, username);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
}

