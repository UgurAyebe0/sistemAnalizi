package ugurayebe.fun.controller.program.node;

import java.util.ArrayList;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class availablesTeacher {
    // Node sınıfı için değişkenler
    public Object Teacher;
    public ArrayList Days;
    public availablesTeacher next;
    public availablesTeacher last;

    // Yapıcı metod
    public availablesTeacher (Object Teacher, ArrayList Days){
        this.Teacher = Teacher;
        this.Days = Days;
        this.next = null;
        this.last = null;
    }

    // Boş bir bitiş node'u oluşturuluyor
    public static availablesTeacher FinishTeacherNode = new availablesTeacher(' ', new ArrayList());

    // Boş bir bitiş node'u oluşturuluyor
    public availablesTeacher() {
        FinishTeacherNode.last = null;
        FinishTeacherNode.next = null;
    }

    // Yeni bir node ekleyen metod
    public static void addNodeList(Object Teacher, ArrayList Days){
        availablesTeacher newNode = new availablesTeacher(Teacher,Days);
        newNode.last = FinishTeacherNode.last;
        FinishTeacherNode.last = newNode;
        newNode.next = FinishTeacherNode;
    }

    // Ana metod - Öğretmenlerin müsaitlik durumlarını oluşturur
    public static void main(){

        // Tüm öğretmenlerin ID'lerini al
        String sql = "SELECT id FROM Teacher";
        ArrayList<Integer> teacherIds = (ArrayList<Integer>) jdbcTemplate.queryForList(sql, Integer.class);

        // Ders sayısını al
        String lessonCountQuery = "SELECT COUNT(*) FROM times WHERE type = ?";
        int lessonCount = jdbcTemplate.queryForObject(lessonCountQuery, Integer.class, "Ders");

        for (Object id : teacherIds){
            // Tüm günleri al
            String daySql = "SELECT Day FROM days";
            ArrayList schoolDay = (ArrayList) jdbcTemplate.queryForList(daySql, String.class);

            // Öğretmenin müsait olmadiği günleri al
            String notDaySql = "SELECT Day FROM availabilityteacher Where Teacher = ?";
            ArrayList notTeacherDay = (ArrayList) jdbcTemplate.queryForList(notDaySql, String.class, id);

            // Tüm günlerden cıkar.
            schoolDay.removeAll(notTeacherDay);

            ArrayList sendActive = new ArrayList();
            // Müsaitlikleri al
            for (Object Day : schoolDay){
                for (int i = 1; i <= lessonCount; i++) {
                    sendActive.add(Day + " " + i);
                }
            }
            // Kaydet
            addNodeList(id,sendActive);
        }
    }
}
