package ugurayebe.fun.view.Days;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ugurayebe.fun.listener.veriables.*;
import static ugurayebe.fun.view.opMenu.opPermission;

public class button {

    public static JButton lessonBackButton() {
        JButton button = new JButton("Geri Git");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                opPermission();
            }
        });
        return button;
    }

    public static JButton lunchBackButton() {
        JButton button = new JButton("Geri Git");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                lessonSettings.main();
            }
        });
        return button;
    }

    public static JButton finishBackButton() {
        JButton button = new JButton("Geri Git");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                lunchSettings.main();
            }
        });
        return button;
    }

    public static JButton lessonNextButton() {
        JButton button = new JButton("Devam Et");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!lessonDurabityComboBox.getText().isEmpty() && !recessDurabityComboBox.getText().isEmpty()) {
                    lessonStartHours = Integer.parseInt((String) lessonStartHoursComboBox.getSelectedItem());
                    lessonMinutesHours = Integer.parseInt((String) lessonMinutesHoursComboBox.getSelectedItem());
                    lessonDurabity = Integer.parseInt(lessonDurabityComboBox.getText());
                    recessDurabity = Integer.parseInt(recessDurabityComboBox.getText());
                    frame.dispose();
                    lunchSettings.main();
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen boş bilgi bırakmayınız.");
                }
            }
        });
        return button;
    }

    public static JButton lunchNextButton() {
        JButton button = new JButton("Devam Et");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (lunchDurabityField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Lütfen öğlen molası süresini girin");
                } else {
                    String selectedTime = (String) lunchHoursComboBox.getSelectedItem();
                    String[] timeParts = selectedTime.split(":");
                    int selectedHour = Integer.parseInt(timeParts[0]);
                    int selectedMinute = Integer.parseInt(timeParts[1]);
                    int totalMinutes = selectedHour * 60 + selectedMinute;
                    lunchHours = totalMinutes;
                    lunchDuration = Integer.parseInt(lunchDurabityField.getText());
                    frame.dispose();
                    finishSettings.main();
                }
            }
        });
        return button;
    }

    public static JButton finishNextButton() {
        JButton button = new JButton("Kaydet");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedTime = lessonFinisComboBox.getSelectedItem().toString();
                String[] parts = selectedTime.split(":");
                if (parts.length == 2) {
                    String selectedHour = parts[0];
                    String selectedMinute = parts[1];
                    lessonFinishHours = Integer.parseInt(selectedHour);
                    lessonFinishMinutes = Integer.parseInt(selectedMinute);
                    frame.dispose();
                    save.main();
                }

            }
        });
        return button;
    }
}
