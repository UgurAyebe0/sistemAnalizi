package ugurayebe.fun.controller.program.node;

import java.util.ArrayList;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class nodeAcademic {
    public Object id;
    public Object episode;
    public Object lesson;
    public Object lessonCode;
    public Object teacher;
    public Object classroom;
    public Object classroomNumber;
    public Object day;
    public Object lessonTime;
    public nodeAcademic next;
    public nodeAcademic last;

    public nodeAcademic(Object id, Object episode, Object lesson, Object lessonCode, Object teacher,
                        Object classroom, Object classroomNumber, Object day, Object lessonTime) {
        this.id = id;
        this.episode = episode;
        this.lesson = lesson;
        this.lessonCode = lessonCode;
        this.teacher = teacher;
        this.classroom = classroom;
        this.classroomNumber = classroomNumber;
        this.day = day;
        this.lessonTime = lessonTime;
        this.next = null;
        this.last = null;
    }


    public static nodeAcademic FinishNodeAcademic = new nodeAcademic(" ", " ", " ", " ", " ", " ", " ", " ", " ");

    public nodeAcademic() {
        FinishNodeAcademic.last = null;
        FinishNodeAcademic.next = null;
    }

    public static void addNodeList(Object id, Object episode, Object lesson, Object lessonCode, Object teacher,
                                   Object classroom, Object classroomNumber, Object day, Object lessonTime) {
        nodeAcademic newNode = new nodeAcademic(id, episode,lesson,lessonCode,teacher,classroom,classroomNumber,day,lessonTime);
        newNode.last = FinishNodeAcademic.last;
        FinishNodeAcademic.last = newNode;
        newNode.next = FinishNodeAcademic;
    }

    public static void main(String columName, String columData) {

        // Ait olan stün sayısını alıyoruz...
        String sql = "SELECT id FROM academic_program where " + columName + " = ?";
        ArrayList<Integer> academicId = (ArrayList<Integer>) jdbcTemplate.queryForList(sql, Integer.class, columData);


        // Buradan Gerekli bilgileri node kayıt etmek için gerekli olan sql cümleleri
        String academicEpisode = "SELECT e.name FROM academic_program a JOIN episode e ON a.episode = e.id WHERE a.id = ?";
        String academicLesson = "SELECT e.name FROM academic_program a JOIN lesson e ON a.lessons = e.id WHERE a.id = ?";
        String academicTeacher = "SELECT e.username FROM academic_program a JOIN teacher e ON a.teacher = e.id WHERE a.id = ?";
        String academicClassroom = "SELECT e.Type FROM academic_program a JOIN Classroom e ON a.Classroom = e.id WHERE a.id = ?";
        String academicCode = "SELECT Lesson_Code from academic_program where id = ?";
        String academicClassroomNumber = "SELECT Classroom_Number from academic_program where id = ?";
        String academicDay = "SELECT Day from academic_program where id = ?";
        String academicLessonTime = "SELECT LessonTime from academic_program where id = ?";


        // Ait olan stün sayısı kadar döndür
        for (Object id : academicId) {

            // Gerekli bilgileri al..
            Object episode = jdbcTemplate.queryForObject(academicEpisode, Object.class, id);
            Object lesson = jdbcTemplate.queryForObject(academicLesson, Object.class, id);
            Object lessonCode = jdbcTemplate.queryForObject(academicCode, Object.class, id);
            Object teacher = jdbcTemplate.queryForObject(academicTeacher, Object.class, id);
            Object classroom = jdbcTemplate.queryForObject(academicClassroom, Object.class, id);
            Object classroomNumber= jdbcTemplate.queryForObject(academicClassroomNumber, Object.class, id);
            Object day= jdbcTemplate.queryForObject(academicDay, Object.class, id);
            Object lessonTime= jdbcTemplate.queryForObject(academicLessonTime, Object.class, id);

            // Gerekli bilgileri kayıt et.
            addNodeList(id, episode,lesson,lessonCode,teacher,classroom,classroomNumber,day,lessonTime);
        }
    }
}

