package ugurayebe.fun.controller.frame;

import ugurayebe.fun.listener.showMessage;

import javax.swing.*;
import java.util.ArrayList;

import static ugurayebe.fun.controller.frame.button.updateButton.alertMesage;
import static ugurayebe.fun.database.config.jdbcTemplate;

public class validData {
    public static boolean valid(ArrayList fieldData, ArrayList fieldOrder, String frameType, Object id, Boolean update) {

        for (int i = 0; i < fieldData.size(); i++) {

            String label = (String) fieldData.get(i);
            String field = (String) fieldOrder.get(i);

            if (label.isEmpty() && !field.startsWith("jC")) {
                showMessage.main(field + " cannot be left blank.");
                return false;
            }

            if (label.length() > 35) {
                showMessage.main(field + " It can be up to 35 characters.");
                return false;
            }


            if (update) {
                switch (frameType) {
                    case "Title":
                        if (countRecords2(frameType, field, label, id)) {
                            showMessage.main(field + "(" + label + ") lütfen benzersiz birşey yapın.");
                            return false;
                        }
                    case "Teacher":
                        if (field.equals("TC") || field.equals("PhoneNumber")) {
                            if (label.length() != 11) {
                                showMessage.main(field + " lütfen 11 rakamlı girin. (" + label.length() + ")");
                                return false;
                            } else if (isDuplicated(field, frameType, label, id)) {
                                showMessage.main(field + " zaten kullanılıyor.");
                                return false;
                            } else if (!containsNumericalDigit(label)) {
                                showMessage.main(field + " bilgisine lütfen sadece rakam girin.");
                                return false;
                            }
                        }
                        if (field.equals("Username")) {
                            if (isDuplicated(field, frameType, label, id)) {
                                showMessage.main(field + " zaten kullanılıyor.");
                                return false;
                            }
                        }
                        break;
                }
            } else {
                switch (frameType) {
                    case "Title":
                        if (countRecords(frameType, field, label)) {
                            showMessage.main(field + " zaten kullanılıyor.");
                            return false;
                        }
                        break;
                    case "Teacher":
                        if (field.equals("TC") || field.equals("PhoneNumber")) {
                            if (label.length() != 11) {
                                showMessage.main(field + " lütfen 11 rakamlı girin. (" + label.length() + ")");
                                return false;
                            } else if (countRecords(frameType, field, label)) {
                                showMessage.main(field + " zaten kullanılıyor.");
                                return false;
                            } else if (!containsNumericalDigit(label)) {
                                showMessage.main(field + " bilgisine lütfen sadece rakam girin.");
                                return false;
                            }
                        }
                        if (field.equals("Username")) {
                            if (countRecords(frameType, field, label)) {
                                showMessage.main(field + " zaten kullanılıyor.");
                                return false;
                            }
                        }
                        break;
                }
            }
        }


        System.out.println("fieldData " + fieldData);
        System.out.println("fieldOrder " + fieldOrder);


        if (frameType.startsWith("academic_program") && update) {

            Object Episode = fieldData.get(2); // Bilgisayar 1
            String sqlEpisode = "SELECT id FROM Episode WHERE Name = '" + Episode + "'";
            int intEpisode = jdbcTemplate.queryForObject(sqlEpisode, Integer.class);

            Object Teacher = fieldData.get(4); // enginucar
            String sqlTeacher = "SELECT id FROM Teacher WHERE Username = '" + Teacher + "'";
            int intTeacher = jdbcTemplate.queryForObject(sqlTeacher, Integer.class);

            Object Day = fieldData.get(5); // Cuma
            Object LessonTime = fieldData.get(6); // 4

            Object Classroom = fieldData.get(7); // Amfi
            Object Classroom_Number = fieldData.get(1); // 2222
            String sqlClassroom = "SELECT id FROM Classroom WHERE Type = '" + Classroom + "'";
            int intClassroom = jdbcTemplate.queryForObject(sqlClassroom, Integer.class);

            String sqlEpisodeAvailables = "SELECT COUNT(*) FROM academic_program" +
                    " WHERE Episode = ? AND Day = ?" +
                    " AND LessonTime = ? AND id != ?";

            int availablesEpisode = jdbcTemplate.queryForObject(
                    sqlEpisodeAvailables,
                    new Object[]{intEpisode, Day, LessonTime, id},
                    Integer.class
            );

            String sqlTeacherAvailables = "SELECT COUNT(*) FROM academic_program" +
                    " WHERE Teacher = ? AND Day = ?" +
                    " AND LessonTime = ? AND id != ?";

            int availablesTeacher = jdbcTemplate.queryForObject(
                    sqlTeacherAvailables,
                    new Object[]{intTeacher, Day, LessonTime, id},
                    Integer.class
            );

            String sqlRoomAvailables = "SELECT COUNT(*) FROM academic_program" +
                    " WHERE Classroom = ? AND Classroom_Number = ? AND Day = ?" +
                    " AND LessonTime = ? AND id != ?";

            int availablesRoom = jdbcTemplate.queryForObject(
                    sqlRoomAvailables,
                    new Object[]{intClassroom,Classroom_Number, Day, LessonTime, id},
                    Integer.class
            );

            System.out.println("availablesRoom " + availablesRoom  + " availablesTeacher " + availablesTeacher
                               + " availablesEpisode " + availablesEpisode );
            System.out.println("id " + id);
            if (availablesEpisode != 0){
                System.out.println(alertMesage);
                alertMesage = alertMesage + "Bu bölümün o gün zaten başka bir dersi var (!) \n";
            }
            if (availablesTeacher != 0){
                alertMesage = alertMesage + "Bu öğretmenin o gün zaten başka bir dersi var (!) \n";
                System.out.println(alertMesage);
            }
            if (availablesRoom != 0){
                System.out.println(alertMesage);
                alertMesage = alertMesage + "Bu dersliğin o gün zaten başka bir dersi var (!) \n";
            }
        }


        if (frameType.startsWith("academic_program") && !update) {

            Object Episode = fieldData.get(2); // Bilgisayar 1
            String sqlEpisode = "SELECT id FROM Episode WHERE Name = '" + Episode + "'";
            int intEpisode = jdbcTemplate.queryForObject(sqlEpisode, Integer.class);

            Object Teacher = fieldData.get(4); // enginucar
            String sqlTeacher = "SELECT id FROM Teacher WHERE Username = '" + Teacher + "'";
            int intTeacher = jdbcTemplate.queryForObject(sqlTeacher, Integer.class);

            Object Day = fieldData.get(5); // Cuma
            Object LessonTime = fieldData.get(6); // 4

            Object Classroom = fieldData.get(7); // Amfi
            String sqlClassroom = "SELECT id FROM Classroom WHERE Type = '" + Classroom + "'";
            int intClassroom = jdbcTemplate.queryForObject(sqlClassroom, Integer.class);

            Object Classroom_Number = fieldData.get(1); // 2222

            String sqlEpisodeAvailables = "SELECT COUNT(*) FROM academic_program" +
                    " WHERE Episode = ? AND Day = ?" +
                    " AND LessonTime = ?";

            int availablesEpisode = jdbcTemplate.queryForObject(
                    sqlEpisodeAvailables,
                    new Object[]{intEpisode, Day, LessonTime},
                    Integer.class
            );

            String sqlTeacherAvailables = "SELECT COUNT(*) FROM academic_program" +
                    " WHERE Teacher = ? AND Day = ?" +
                    " AND LessonTime = ?";

            int availablesTeacher = jdbcTemplate.queryForObject(
                    sqlTeacherAvailables,
                    new Object[]{intTeacher, Day, LessonTime},
                    Integer.class
            );

            String sqlRoomAvailables = "SELECT COUNT(*) FROM academic_program" +
                    " WHERE Classroom = ? AND Classroom_Number = ? AND Day = ?" +
                    " AND LessonTime = ?";

            int availablesRoom = jdbcTemplate.queryForObject(
                    sqlRoomAvailables,
                    new Object[]{intClassroom,Classroom_Number, Day, LessonTime},
                    Integer.class
            );


            if (availablesEpisode != 0){
                System.out.println(alertMesage);
                alertMesage = alertMesage + "Bu bölümün o gün zaten başka bir dersi var (!) \n";
            }
            if (availablesTeacher != 0){
                alertMesage = alertMesage + "Bu öğretmenin o gün zaten başka bir dersi var (!) \n";
                System.out.println(alertMesage);
            }
            if (availablesRoom != 0){
                System.out.println(alertMesage);
                alertMesage = alertMesage + "Bu dersliğin o gün zaten başka bir dersi var (!) \n";
            }

            if (availablesRoom + availablesTeacher + availablesEpisode != 0) {
                int cevap = JOptionPane.showConfirmDialog(null, alertMesage, "Uyarı", JOptionPane.YES_NO_OPTION);

                if (cevap == JOptionPane.YES_OPTION) {
                    System.out.println("Evet seçildi, true döndürülüyor.");
                    // İşlemlerinizi gerçekleştirin...
                    return true;
                } else {
                    System.out.println("Hayır seçildi, false döndürülüyor.");
                    return false;
                }
            }




        }
        return true;
    }


    private static boolean isDuplicated(String field, String process, String label, Object id) {
        String sql = "SELECT COUNT(*) FROM " + process + " WHERE " + field + " = ? AND id != ?";
        return whereBoolean(sql, new Object[]{label, id});
    }

    public static boolean countRecords(String frameType, String field, String label) {
        String sql = "SELECT COUNT(*) FROM " + frameType + " WHERE " + field + " = ?";
        Object[] params = {label};
        return 0 != jdbcTemplate.queryForObject(sql, Integer.class, params);
    }

    public static boolean countRecords2(String frameType, String field, String label, Object id) {
        String sql = "SELECT COUNT(*) FROM " + frameType + " WHERE " + field + " = ? AND id != ?";
        Object[] params = {label, id};
        return 0 != jdbcTemplate.queryForObject(sql, Integer.class, params);
    }


    private static boolean whereBoolean(String sql, Object[] params) {
        return jdbcTemplate.queryForObject(sql, Integer.class, params) > 0;
    }

    private static boolean containsNumericalDigit(String input) {
        return input.matches(".*\\d.*");
    }

}
