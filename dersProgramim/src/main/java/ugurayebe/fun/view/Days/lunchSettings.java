package ugurayebe.fun.view.Days;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


import static ugurayebe.fun.listener.veriables.*;
import static ugurayebe.fun.view.Days.button.lunchBackButton;
import static ugurayebe.fun.view.Days.button.lunchNextButton;

public class lunchSettings {
    public static void main() {

        // Öğlen yemeğini hesaplattırdım.

        frame = new JFrame("Yönetici | Öğlen Saati Belirleme");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        lunchHoursComboBox = new JComboBox<>(numbers(lessonStartHours, lessonMinutesHours, lessonDurabity, recessDurabity));
        lunchDurabityField = new JTextField(10);

        JButton saveButton = lunchNextButton();
        JButton cancelButton = lunchBackButton();

        panel.add(new JLabel("Öğlen Molası Başlangıcı"));
        panel.add(lunchHoursComboBox);
        panel.add(new JLabel("Öğlen Molası Süresi"));
        panel.add(lunchDurabityField);
        panel.add(cancelButton);
        panel.add(saveButton);
        frame.add(panel);
        frame.setVisible(true);
    }



    /// Buradada öğlen yemeğinin olabileceği saatleri belirledim.
    // Derse göre ilerliyor
    private static String[] numbers(int hours, int minutes, int lesson, int recess) {
        ArrayList<String> number = new ArrayList<>();
        int işlem = 0;
        for (int i = 0; işlem <= 1440 + ((hours - 4) * 60) + minutes; i++) {
            işlem = (hours * 60) + minutes + ((i + 1) * lesson) + (i * recess);
            int hour = işlem / 60;
            int minut = işlem % 60;
            if (hour >= 24) {
                hour -= 24;
                number.add(String.format("%02d:%02d", hour, minut));
            } else {
                number.add(String.format("%02d:%02d", hour, minut));
            }
        }
        return number.toArray(new String[0]);
    }
}
