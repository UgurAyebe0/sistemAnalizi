package ugurayebe.fun.controller.program;


import java.util.ArrayList;
import java.util.HashSet;

import static ugurayebe.fun.controller.program.alert.notClassroom.classroomAlert;
import static ugurayebe.fun.controller.program.data.dataGet.getNodeClassroom;
import static ugurayebe.fun.database.config.jdbcTemplate;

public class branch1Availables {
    public static ArrayList<String> getTeacherAvailables(ArrayList<String> teacherDay, ArrayList<String> episodeDay, int lessonTime, Object Classroom, Object lessonName) {

        System.out.println("cccccccccc " + teacherDay );
        for (String day : teacherDay) {
            String[] slot = day.split(" ", 2);
            String selectDay = slot[0];
            int selectTime = Integer.parseInt(slot[1]);

            ArrayList<String> control = new ArrayList<>();
            for (int i = 0; i < lessonTime; i++) {
                control.add(selectDay + " " + (selectTime + i));
            }

            HashSet<String> teacherDaySet = new HashSet<>(teacherDay);
            HashSet<String> episodeDaySet = new HashSet<>(episodeDay);

            int cont = 0;
            for (String otherController : control) {
                if (teacherDaySet.contains(otherController) && episodeDaySet.contains(otherController)) {
                    cont++;
                }
            }
            if (cont == lessonTime) {

                ArrayList returnList = getRoomAvailables(Classroom, control, lessonTime);

                String sql = "SELECT id FROM classroom";
                ArrayList<Integer> classroomIds = (ArrayList<Integer>) jdbcTemplate.queryForList(sql, Integer.class);

                while (returnList == null) {
                    classroomIds.remove(Classroom);
                    Classroom = classroomAlert(classroomIds, lessonName);
                    returnList = getRoomAvailables(Classroom, control, lessonTime);
                }

                if (returnList != null) {
                    return returnList;
                }
            }
        }
        return null;
    }

    public static ArrayList getRoomAvailables(Object Classroom, ArrayList<String> control, int lessonTime) {


        System.out.println("Classroom : " + Classroom);
        // Miktarı alıyoruz.
        String customSql = "SELECT Custom FROM classroom Where id = ?";
        int custom = jdbcTemplate.queryForObject(customSql, Integer.class, Classroom);
        System.out.println("custom : " + custom);

        for (int i = 1; i <= custom; i++) {
            ArrayList ClassroomDay = getNodeClassroom(Integer.parseInt(Classroom.toString()), i);
            System.out.println("ClassroomDay : " + ClassroomDay);

            int cont = 0;
            for (String otherController : control) {
                if (ClassroomDay.contains(otherController)) {
                    cont++;
                    System.out.println("Cont Onaylandı.");
                }
            }
            if (cont == lessonTime) {
                control.add(Classroom + " " + i);
                return control;
            }
        }
        return null;
    }
}
