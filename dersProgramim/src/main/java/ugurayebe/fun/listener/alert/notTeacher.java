package ugurayebe.fun.listener.alert;

import javax.swing.*;
import java.util.ArrayList;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class notTeacher {
    public static int teacherAlert(ArrayList teacherID, Object lessonName) {

        String[] secenekler = new String[teacherID.size()];

        for (int i = 0; i < teacherID.size(); i++) {

            String classroomNameSql = "SELECT username FROM teacher Where id = ?";
            String classroomName = jdbcTemplate.queryForObject(classroomNameSql, String.class, teacherID.get(i));
            secenekler[i] = classroomName;
        }

        JComboBox<String> comboBox = new JComboBox<>(secenekler);

        // ComboBox'ı içeren bir panel oluştur
        JPanel panel = new JPanel();
        panel.add(new JLabel(lessonName + " dersi için uygun öğretmen seçiniz "));
        panel.add(comboBox);

        // Kullanıcıya ComboBox'ı içeren bir dialog penceresi göster
        int result = JOptionPane.showConfirmDialog(null, panel, "Öğretmen Seçimi", JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String selectedTeacher = (String) comboBox.getSelectedItem();
            String classroomIdSql = "SELECT id FROM teacher Where username = ?";

            System.out.println("deleted : " + jdbcTemplate.queryForObject(classroomIdSql, Integer.class, selectedTeacher));
            return jdbcTemplate.queryForObject(classroomIdSql, Integer.class, selectedTeacher);
        } else {
            // Kullanıcı Cancel düğmesine bastıysa veya pencereyi kapattıysa
            JOptionPane.showMessageDialog(null, "Öğretmen seçimi iptal edildi.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            return 0;
        }
    }
}
