package ugurayebe.fun.view.Days;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static ugurayebe.fun.listener.veriables.*;
import static ugurayebe.fun.view.Days.button.finishBackButton;
import static ugurayebe.fun.view.Days.button.finishNextButton;


public class finishSettings {

    public static void main() {
        frame = new JFrame("Yönetici | Ders bitişi ayarlama");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2));

        lessonFinisComboBox = new JComboBox<>(numbers(lessonStartHours, lessonMinutesHours, lessonDurabity, recessDurabity));
        JButton cancelButton = finishBackButton();
        JButton saveButton = finishNextButton();

        panel.add(new JLabel("Ders Bitiş Saati"));
        panel.add(lessonFinisComboBox);
        panel.add(cancelButton);
        panel.add(saveButton);
        frame.add(panel);
        frame.setVisible(true);
    }


    private static String[] numbers(int hours, int minutes, int lesson, int recess) {
        ArrayList<String> number = new ArrayList<>();
        int işlem = 0;
        for (int i = 0; işlem <= 1440 + ((hours - 4) * 60) + minutes; i++) {
            işlem = lunchHours + lunchDuration + ((i + 1) * lesson) + (i * recess);
            int hour = işlem / 60;
            int minut = işlem % 60;
            if (hour >= 24) {
                hour = hour - 24;
                number.add(String.format("%02d:%02d", hour, minut));
            } else {
                number.add(String.format("%02d:%02d", hour, minut));
            }
        }
        return number.toArray(new String[0]);
    }

}
