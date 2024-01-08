package ugurayebe.fun.listener.alert;

import ugurayebe.fun.controller.frame.createFrame;

import javax.swing.*;
import java.util.ArrayList;

import static ugurayebe.fun.database.config.jdbcTemplate;
import static ugurayebe.fun.view.opMenu.opPermission;

public class getEpisode {
    public static void getEpisode() {

        String sqlEpisodeId = "SELECT id FROM episode";
        ArrayList EpisodeId = (ArrayList) jdbcTemplate.queryForList(sqlEpisodeId, Integer.class);

        String[] secenekler = new String[EpisodeId.size()];


        for (int i = 0; i < EpisodeId.size(); i++) {
            String sqlUsername = "Select Name from Episode Where id = ?";
            String classroomName = jdbcTemplate.queryForObject(sqlUsername, String.class, EpisodeId.get(i));
            secenekler[i] = classroomName;
        }

        JComboBox<String> comboBox = new JComboBox<>(secenekler);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Hangi bölümün ders programına bakacaksınız ?"));
        panel.add(comboBox);

        // Kullanıcıya ComboBox'ı içeren bir dialog penceresi göster
        int result = JOptionPane.showConfirmDialog(null, panel, "Ders Programı için bölüm seçimi", JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String selectedEpisode = (String) comboBox.getSelectedItem();
            String sql = "Select id from Episode Where Name = ?";
            String classroomName = jdbcTemplate.queryForObject(sql, String.class, selectedEpisode);


            ArrayList fieldName = new ArrayList();
            fieldName.add("jComboBoxEpisode");
            fieldName.add("jComboBoxLessons");
            fieldName.add("jComboBoxTeacher");
            fieldName.add("jComboBoxDay");
            fieldName.add("jComboBoxLessonTime");
            fieldName.add("jComboBoxClassroom");
            fieldName.add("Lesson_Code");
            fieldName.add("Classroom_Number");


            createFrame.main(selectedEpisode + " - Öğretmen Ders Programı", 1000, 600, fieldName, 4,
                    "academic_program_episode", "Select id,Episode,Lessons,Teacher,Day,LessonTime,Classroom,Lesson_Code,Classroom_Number" +
                            " from academic_program Where Episode = '" + classroomName + "' ORDER BY Episode");


        } else {
            JOptionPane.showMessageDialog(null, "Ders Programına Bakılması İptal Edildi", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            opPermission();
        }
    }
}
