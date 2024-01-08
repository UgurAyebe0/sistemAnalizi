package ugurayebe.fun.controller.program;

import ugurayebe.fun.controller.program.node.nodeLesson;
import ugurayebe.fun.listener.showMessage;

import java.util.ArrayList;

import static ugurayebe.fun.listener.alert.notTeacher.teacherAlert;
import static ugurayebe.fun.controller.program.branch2Availables.getTeacherAvailables2;
import static ugurayebe.fun.controller.program.data.dataGet.*;
import static ugurayebe.fun.controller.program.data.dataSave.sendAcademicProgram;
import static ugurayebe.fun.controller.program.data.dataSet.*;
import static ugurayebe.fun.controller.program.node.nodeLesson.FinishLessonNode;
import static ugurayebe.fun.controller.program.branch1Availables.*;
import static ugurayebe.fun.database.config.jdbcTemplate;

public class start {
    public static void starlend() {

        nodeLesson lesson = new nodeLesson();
        lesson.main();

        nodeLesson active = FinishLessonNode.last;

        while (active != null) {

            ArrayList<String> teacherDay = new ArrayList<>();
            ArrayList<String> episodeDay = new ArrayList<>();
            try {
                teacherDay = getNodeTeacher(active.Teacher.get(0));
                episodeDay = getNodeEpisode(active.Episode.get(0));
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                if (teacherDay.size() == 0) {
                    showMessage.main("Lütfen " + active.Lesson + " Dersine öğretmen atayın.");
                } else if (episodeDay.size() == 0) {
                    showMessage.main("Lütfen " + active.Lesson + " Dersine bölüm atayın.");
                }
            }


            String Timec = (String) active.Time;
            int Time = Integer.parseInt(Timec);
            if (!controlTeacher(teacherDay, episodeDay, Time)) {
                String sql = "SELECT id FROM Teacher";
                ArrayList<Integer> teacherIds = (ArrayList<Integer>) jdbcTemplate.queryForList(sql, Integer.class);
                teacherIds.remove(active.Teacher);
                active.Teacher.set(0, teacherAlert(teacherIds, active.Lesson));
            }

            if (active.Branch.equals("1")) {


                ArrayList availablesDay = getTeacherAvailables(getNodeTeacher(active.Teacher.get(0)), getNodeEpisode(active.Episode.get(0)), Integer.parseInt(active.Time.toString()), active.Classroom, active.Lesson);

                String liste1 = (String) availablesDay.get(0);
                String[] parts = liste1.split(" ");


                while (parts.length != 2) {
                    System.out.println("Parts size = " + parts.length);
                    availablesDay = getTeacherAvailables(getNodeTeacher(active.Teacher.get(0)), getNodeEpisode(active.Episode.get(0)), Integer.parseInt(active.Time.toString()), active.Classroom, active.Lesson);
                }

                // Teacher ve episode müsait günü alıyoruz ve günleri siliyoruz.
                setNodeTeacher(active.Teacher.get(0), availablesDay);
                setNodeEpisode(active.Episode.get(0), availablesDay);

                liste1 = (String) availablesDay.get(availablesDay.size() - 1);
                parts = liste1.split(" ");
                String classroom = parts[0];
                String stringCustom = parts[1];
                int Custom = Integer.parseInt(stringCustom);
                int Classroom = Integer.parseInt(classroom);
                setNodeClassroom(Classroom, Custom, availablesDay);

                for (int i = 0; i < Integer.parseInt(active.Time.toString()); i++) {
                    String day = (String) availablesDay.get(i);
                    String[] part = day.split(" ");
                    String Day = part[0];
                    String lessonTime = part[1];
                    sendAcademicProgram(active.Episode.get(0), active.lessonId, active.Code, active.Teacher.get(0), Classroom, Custom, Day, lessonTime);
                }
            } else {
                if (active.Teacher.size() == 1) {
                    for (int j = 1; j <= 2; j++) {
                        // Müsait günü ve Classroom alıyoruz.
                        ArrayList availablesDay = getTeacherAvailables2(getNodeTeacher(active.Teacher.get(0)), getNodeEpisode(active.Episode.get(0)), Integer.parseInt(active.Time.toString()), active.Classroom, active.Lesson, j);

                        // Müsait günü öğretmenden siliyoruz.
                        setNodeTeacher(active.Teacher.get(0), availablesDay);


                        // Müsait günü bölümden siliyoruz.
                        setNodeEpisode2(active.Episode.get(0), availablesDay, String.valueOf(j));


                        // Müsait günü Derslikten siliyoruz.
                        String liste1 = (String) availablesDay.get(availablesDay.size() - 1);
                        String[] parts = liste1.split(" ");
                        String classroom = parts[0];
                        String stringCustom = parts[1];
                        int Custom = Integer.parseInt(stringCustom);
                        int Classroom = Integer.parseInt(classroom);


                        for (Object e : availablesDay) {
                            System.out.println(e);
                        }
                        for (int i = 0; i < availablesDay.size() - 1; i++) {
                            String liste2 = (String) availablesDay.get(i);
                            String[] part = liste2.split(" ");
                            String day = part[0];
                            String custom = part[1];
                            availablesDay.set(i, day + " " + custom);
                        }

                        System.out.println("availablesDay : " + availablesDay);

                        setNodeClassroom(Classroom, Custom, availablesDay);


                        for (int i = 0; i < Integer.parseInt(active.Time.toString()); i++) {
                            String day = (String) availablesDay.get(i);
                            String[] part = day.split(" ");
                            String Day = part[0];
                            String lessonTime = part[1];
                            sendAcademicProgram(active.Episode.get(0), active.lessonId, active.Code + "/" + j, active.Teacher.get(0), Classroom, Custom, Day, lessonTime);
                        }
                    }
                } else {
                    for (int j = 1; j <= 2; j++) {
                        // Müsait günü ve Classroom alıyoruz.
                        ArrayList availablesDay = getTeacherAvailables2(getNodeTeacher(active.Teacher.get(j - 1)), getNodeEpisode(active.Episode.get(0)), Integer.parseInt(active.Time.toString()), active.Classroom, active.Lesson, j);

                        // Müsait günü öğretmenden siliyoruz.
                        setNodeTeacher(active.Teacher.get(0), availablesDay);

                        // Müsait günü bölümden siliyoruz.
                        setNodeEpisode2(active.Episode.get(0), availablesDay, String.valueOf(j));

                        // Müsait günü Derslikten siliyoruz.
                        String liste1 = (String) availablesDay.get(availablesDay.size() - 1);
                        String[] parts = liste1.split(" ");
                        String classroom = parts[0];
                        String stringCustom = parts[1];

                        int Custom = Integer.parseInt(stringCustom);
                        int Classroom = Integer.parseInt(classroom);
                        System.out.println("Silinen değer Classroom: " + Classroom);
                        System.out.println("Silinen değer Custom: " + Custom);
                        System.out.println("Silinen değer availablesDay: " + availablesDay);

                        for (int i = 0; i < availablesDay.size() - 1; i++) {
                            String liste2 = (String) availablesDay.get(i);
                            String[] part = liste2.split(" ");
                            String day = part[0];
                            String custom = part[1];
                            availablesDay.set(i, day + " " + custom);
                        }

                        System.out.println("Silinen değer Classroom: " + Classroom);
                        System.out.println("Silinen değer Custom: " + Custom);
                        System.out.println("Silinen değer availablesDay: " + availablesDay);


                        setNodeClassroom(Classroom, Custom, availablesDay);


                        for (int i = 0; i < Integer.parseInt(active.Time.toString()); i++) {
                            String day = (String) availablesDay.get(i);
                            String[] part = day.split(" ");
                            String Day = part[0];
                            String lessonTime = part[1];
                            sendAcademicProgram(active.Episode.get(0), active.lessonId, active.Code + "/" + j, active.Teacher.get(j - 1), Classroom, Custom, Day, lessonTime);
                        }
                    }
                }
            }


            active = active.last;

        }


    }


    public static boolean controlTeacher(ArrayList<String> teacherDay, ArrayList<String> episodeDay, int lessonTime) {

        for (int i = 0; i < teacherDay.size(); i++) {
            String[] Slot = teacherDay.get(i).split(" ");
            String Day = Slot[0];
            int Time = Integer.parseInt(Slot[1]);


            int sayaç = 0;

            for (int j = 0; j < lessonTime; j++) {
                if (teacherDay.contains(Day + " " + (Time + j))) {
                    sayaç++;
                }
            }

            if (sayaç == lessonTime) {

                int sayaç2 = 0;

                for (int j = 0; j < lessonTime; j++) {
                    if (episodeDay.contains(Day + " " + (Time + j))) {
                        sayaç2++;
                    }
                }
                if (sayaç2 == lessonTime) {

                    return true;
                }
            }
        }
        return false;
    }


}
