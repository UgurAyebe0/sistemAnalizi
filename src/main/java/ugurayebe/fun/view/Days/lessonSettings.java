package ugurayebe.fun.view.Days;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static ugurayebe.fun.listener.veriables.*;
import static ugurayebe.fun.controller.frame.button.backButton.backButton;
import static ugurayebe.fun.view.Days.button.lessonBackButton;
import static ugurayebe.fun.view.Days.button.lessonNextButton;

public class lessonSettings {
    public static void main() {


        // Burada ders başlangıç tarigini ve bitiş, ders süresi ve tenefüs süresini almak için
        // Bir pencere oluşturdum.

        frame = new JFrame("Yönetici | Ders Saati Belirleme");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        lessonStartHoursComboBox = new JComboBox<>(numbers(0, 23));
        lessonMinutesHoursComboBox = new JComboBox<>(numbers(0, 59));
        lessonDurabityComboBox = new JTextField(10);
        recessDurabityComboBox = new JTextField(10);

        JButton saveButton = lessonNextButton();
        JButton cancelButton = lessonBackButton();

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Ders başlangıç saati"));
        panel.add(lessonStartHoursComboBox);
        panel.add(new JLabel("Ders bitiş dakikası"));
        panel.add(lessonMinutesHoursComboBox);
        panel.add(new JLabel("Ders Süresi (dk):"));
        panel.add(lessonDurabityComboBox);
        panel.add(new JLabel("Tenefüs Süresi (dk):"));
        panel.add(recessDurabityComboBox);
        panel.add(cancelButton);
        panel.add(saveButton);
        frame.add(panel);
        frame.setVisible(true);
    }


    // Burada random sayı üretip combobaxa yolladım.
    private static String[] numbers(int start, int end) {
        ArrayList<String> number = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (i < 10)
            {
                number.add("0"+i);
            }else{
                number.add(String.valueOf(i));
            }
        }
        return number.toArray(new String[0]);
    }
}
