package ugurayebe.fun.controller.program.node;

import java.util.ArrayList;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class availablesTeacher {
    public Object Teacher;
    public ArrayList Days;
    public availablesTeacher next;
    public availablesTeacher last;

    public availablesTeacher (Object Teacher, ArrayList Days){
        this.Teacher = Teacher;
        this.Days = Days;
        this.next = null;
        this.last = null;
    }

    public static availablesTeacher FinishTeacherNode = new availablesTeacher(' ', new ArrayList());

    public availablesTeacher() {
        FinishTeacherNode.last = null;
        FinishTeacherNode.next = null;
    }

    public static void addNodeList(Object Teacher, ArrayList Days){
        availablesTeacher newNode = new availablesTeacher(Teacher,Days);
        newNode.last = FinishTeacherNode.last;
        FinishTeacherNode.last = newNode;
        newNode.next = FinishTeacherNode;
    }
    public static void listing (){
        availablesTeacher active = FinishTeacherNode;
        while (active != null){
            System.out.println("-*-*-*-*-*-*-*-*-");
            System.out.println("Öğretmen: " + active.Teacher);
            System.out.println("Müsait Günler: " + active.Days);
            System.out.println("-*-*-*-*-*-*-*-*-");
            active = active.last;
        }
    }


    public static void main(){

        String sql = "SELECT id FROM Teacher";
        ArrayList<Integer> teacherIds = (ArrayList<Integer>) jdbcTemplate.queryForList(sql, Integer.class);

        String lessonCountQuery = "SELECT COUNT(*) FROM times WHERE type = ?";
        int lessonCount = jdbcTemplate.queryForObject(lessonCountQuery, Integer.class, "Ders");

        for (Object id : teacherIds){

            String daySql = "SELECT Day FROM days";
            ArrayList schoolDay = (ArrayList) jdbcTemplate.queryForList(daySql, String.class);

            String notDaySql = "SELECT Day FROM availabilityteacher Where Teacher = ?";
            ArrayList notTeacherDay = (ArrayList) jdbcTemplate.queryForList(notDaySql, String.class, id);

            schoolDay.removeAll(notTeacherDay);

            ArrayList sendActive = new ArrayList();

            for (Object Day : schoolDay){
                for (int i = 1; i <= lessonCount; i++) {
                    sendActive.add(Day + " " + i);
                }
            }
            addNodeList(id,sendActive);
        }
    }
}
