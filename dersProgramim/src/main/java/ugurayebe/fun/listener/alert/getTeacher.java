package ugurayebe.fun.listener.alert;

import ugurayebe.fun.controller.frame.createFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static ugurayebe.fun.database.config.jdbcTemplate;
import static ugurayebe.fun.view.opMenu.opPermission;

public class getTeacher {
    public static void getTeachers() {

        String sqlTeacherId = "SELECT id FROM teacher";
        ArrayList teacherId = (ArrayList) jdbcTemplate.queryForList(sqlTeacherId, Integer.class);

        String[] secenekler = new String[teacherId.size()];


        for (int i = 0; i < teacherId.size(); i++) {
            String sqlUsername = "Select username from Teacher Where id = ?";
            String classroomName = jdbcTemplate.queryForObject(sqlUsername, String.class, teacherId.get(i));
            secenekler[i] = classroomName;
        }

        JComboBox<String> comboBox = new JComboBox<>(secenekler);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Hangi öğretmenin ders programına bakacaksınız ?"));
        panel.add(comboBox);

        // Kullanıcıya ComboBox'ı içeren bir dialog penceresi göster
        int result = JOptionPane.showConfirmDialog(null, panel, "Ders Programı için öğretmen seçimi", JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String selectedTeacher = (String) comboBox.getSelectedItem();
            String sql = "Select id from Teacher Where username = ?";
            String classroomName = jdbcTemplate.queryForObject(sql, String.class, selectedTeacher);


            ArrayList fieldName = new ArrayList();
            fieldName.add("jComboBoxEpisode");
            fieldName.add("jComboBoxLessons");
            fieldName.add("jComboBoxTeacher");
            fieldName.add("jComboBoxDay");
            fieldName.add("jComboBoxLessonTime");
            fieldName.add("jComboBoxClassroom");
            fieldName.add("Lesson_Code");
            fieldName.add("Classroom_Number");


            createFrame.main(selectedTeacher + " - Öğretmen Ders Programı", 1000, 600, fieldName, 4,
                    "academic_program_teacher", "Select id,Episode,Lessons,Teacher,Day,LessonTime,Classroom,Lesson_Code,Classroom_Number" +
                            " from academic_program Where Teacher = '" + classroomName + "' ORDER BY Episode");


        } else {
            JOptionPane.showMessageDialog(null, "Ders Programına Bakılması İptal Edildi", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            opPermission();
        }
    }
}
