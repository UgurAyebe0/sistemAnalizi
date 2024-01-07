package ugurayebe.fun.controller.program;


import java.util.ArrayList;
import java.util.HashSet;

import static ugurayebe.fun.controller.program.alert.notClassroom.classroomAlert;
import static ugurayebe.fun.controller.program.branch1Availables.*;
import static ugurayebe.fun.database.config.jdbcTemplate;

public class branch2Availables {
    public static ArrayList<String> getTeacherAvailables2(ArrayList<String> teacherDay, ArrayList<String> episodeDay, int lessonTime, Object Classroom, Object lessonName, int bakanacak) {


        for (String day : teacherDay) {
            String[] slot = day.split(" ", 2);
            String selectDay = slot[0];
            int selectTime = Integer.parseInt(slot[1]);
            ArrayList<String> control = new ArrayList<>();

            for (int i = 0; i < lessonTime; i++) {
                control.add(selectDay + " " + (selectTime + i) + " " + bakanacak);
            }

            HashSet<String> teacherDaySet = new HashSet<>(teacherDay);
            HashSet<String> episodeDaySet = new HashSet<>(episodeDay);


            int cont = 0;


            for (String otherController : control) {

                String[] parts = otherController.split(" ");
                String input1 = parts[0];
                String input2 = parts[1];

                if (teacherDaySet.contains(input1 + " " + input2)) {
                    if (episodeDaySet.contains(input1 + " " + input2) || episodeDaySet.contains(otherController)) {
                        cont++;
                    }
                }
            }

            for (int i = 0; i < control.size(); i++) {
                String[] parts = control.get(i).split(" ");
                String input1 = parts[0];
                String input2 = parts[1];

                control.set(i,input1 + " " + input2);
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

        return getTeacherAvailables(teacherDay, episodeDay, lessonTime, Classroom, lessonName);
    }
}
