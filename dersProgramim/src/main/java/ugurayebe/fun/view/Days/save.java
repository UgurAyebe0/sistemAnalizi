package ugurayebe.fun.view.Days;


import javax.swing.*;

import static ugurayebe.fun.database.config.jdbcTemplate;
import static ugurayebe.fun.listener.veriables.*;
import static ugurayebe.fun.view.opMenu.opPermission;


public class save {
    public static void main() {

        jdbcTemplate.update("DELETE FROM times");

        int i = 1;
        int start = lessonStartHours * 60 + lessonMinutesHours;

        boolean ekleme = true;

        while (start <= (lessonFinishHours * 60 + lessonFinishMinutes)) {

            if (start == lunchHours) {
                start += lunchDuration;
                ekleme = true;
                i++;

                String sql = "INSERT INTO times(Number, Type, Start, Finish) VALUES (?, ?, ?, ?)";
                jdbcTemplate.update(sql, 0, "Öğlen", lunchHours, (lunchHours + lunchDuration));
            }

            if (!ekleme) {
                String sql = "INSERT INTO times(Number,Type,Start,Finish) Values (?,?,?,?)";
                jdbcTemplate.update(sql,i,"Tenefüs",start,(start + recessDurabity));
                start += recessDurabity;
                i++;
                ekleme = true;
            }

            if (ekleme) {
                String sql ="INSERT INTO times(Number,Type,Start,Finish) Values (?,?,?,?)";
                jdbcTemplate.update(sql,i,"Ders",start,(start + lessonDurabity));
                start += lessonDurabity;
                ekleme = false;
            }

            if (start == lessonFinishHours * 60 + lessonFinishMinutes) {
                System.out.println("Okul Bitti");
                break;
            }
        }
        JOptionPane.showMessageDialog(null, "Başarıyla Ayarları güncellediniz.");
        opPermission();

    }
}