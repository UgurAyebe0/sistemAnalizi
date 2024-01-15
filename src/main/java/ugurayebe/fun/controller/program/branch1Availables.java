package ugurayebe.fun.controller.program;


import java.util.ArrayList;
import java.util.HashSet;

import static ugurayebe.fun.listener.alert.notClassroom.classroomAlert;
import static ugurayebe.fun.controller.program.data.dataGet.getNodeClassroom;
import static ugurayebe.fun.database.config.jdbcTemplate;

public class branch1Availables {

    /**
     * Öğretmenin uygun gün ve saatlerini kontrol eder.
     *
     * @param teacherDay  Öğretmenin uygun gün ve saatlerini içeren liste
     * @param episodeDay  Bölüm planlı gün ve saatlerini içeren liste
     * @param lessonTime  Dersin süresi (kaç saat olduğu)
     * @param Classroom   Dersin gerçekleştirileceği sınıf bilgisi
     * @param lessonName  Dersin adı
     * @return            Dersin gerçekleştirilebileceği uygun gün ve saatleri içeren liste
     */
    public static ArrayList<String> getTeacherAvailables(ArrayList<String> teacherDay,
                                                         ArrayList<String> episodeDay, int lessonTime,
                                                         Object Classroom, Object lessonName)
    {
        // Öğretmenin uygun olduğu gün ve saatleri kontrol etmek için döngü
        for (String day : teacherDay) {
            String[] slot = day.split(" ", 2);
            String selectDay = slot[0];
            int selectTime = Integer.parseInt(slot[1]);

            // Bölüm planlı olduğu gün ve saat aralığını kontrol etmek için döngü
            ArrayList<String> control = new ArrayList<>();
            for (int i = 0; i < lessonTime; i++) {
                control.add(selectDay + " " + (selectTime + i));
            }

            // HashSet'ler kullanılarak öğretmenin ve dersin gün ve saatleri kontrol edilir
            HashSet<String> teacherDaySet = new HashSet<>(teacherDay);
            HashSet<String> episodeDaySet = new HashSet<>(episodeDay);

            int cont = 0;
            // Bölüm ve öğretmenin uygun olduğu gün ve saatleri saymak için döngü
            for (String otherController : control) {
                if (teacherDaySet.contains(otherController) && episodeDaySet.contains(otherController)) {
                    cont++;
                }
            }
            // Eğer bölüm ve öğretmenin uygun olduğu gün ve saatler tam olarak eşleşiyorsa
            if (cont == lessonTime) {
                // Sınıfın uygun olduğu gün ve saatleri kontrol eder
                ArrayList returnList = getRoomAvailables(Classroom, control, lessonTime);

                // Eğer sınıf uygun değilse uygun bir sınıf belirler
                String sql = "SELECT id FROM classroom";
                ArrayList<Integer> classroomIds = (ArrayList<Integer>) jdbcTemplate.queryForList(sql, Integer.class);

                // Uygun sınıf belirlenecek kadar döngü yapar
                while (returnList == null) {
                    classroomIds.remove(Classroom);
                    Classroom = classroomAlert(classroomIds, lessonName);
                    returnList = getRoomAvailables(Classroom, control, lessonTime);
                }

                // Eğer uygun sınıf bulunduysa, uygun gün ve saatleri döndürür
                if (returnList != null) {
                    return returnList;
                }
            }
        }
        // Uygun gün ve saat bulunamazsa null döndürür
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
