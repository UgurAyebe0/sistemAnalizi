package ugurayebe.fun.listener.alert;

import javax.swing.*;
import java.util.ArrayList;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class notClassroom {

    public static int classroomAlert(ArrayList classroom, Object lessonName) {


        String[] secenekler = new String[classroom.size()];

        for (int i = 0; i < classroom.size(); i++) {

            String classroomNameSql = "SELECT Type FROM classroom Where id = ?";
            String classroomName = jdbcTemplate.queryForObject(classroomNameSql, String.class, classroom.get(i));
            secenekler[i] = classroomName;
        }

        JComboBox<String> comboBox = new JComboBox<>(secenekler);

        // ComboBox'ı içeren bir panel oluştur
        JPanel panel = new JPanel();
        panel.add(new JLabel(lessonName + " dersi için uygun derslik seçiniz "));
        panel.add(comboBox);

        // Kullanıcıya ComboBox'ı içeren bir dialog penceresi göster
        int result = JOptionPane.showConfirmDialog(null, panel, "Derslik Seçimi", JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String selectedTeacher = (String) comboBox.getSelectedItem();
            String classroomIdSql = "SELECT id FROM classroom Where Type = ?";

            System.out.println("deleted : " + jdbcTemplate.queryForObject(classroomIdSql, Integer.class, selectedTeacher));
            return jdbcTemplate.queryForObject(classroomIdSql, Integer.class, selectedTeacher);
        } else {
            // Kullanıcı Cancel düğmesine bastıysa veya pencereyi kapattıysa
            JOptionPane.showMessageDialog(null, "Derslik seçimi iptal edildi.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            return 0;
        }
    }
}
