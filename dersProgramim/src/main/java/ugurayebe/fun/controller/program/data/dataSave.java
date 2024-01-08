package ugurayebe.fun.controller.program.data;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class dataSave {

    public static void sendAcademicProgram(Object Episode, Object Lesson, Object Lesson_Code,
                                           Object Teacher, Object Classroom, Object Classroom_Number,
                                           Object Day, Object LessonTime){

        String sql = "INSERT INTO academic_program (Episode, Lessons, Lesson_Code, Teacher, Classroom, Classroom_Number, Day, LessonTime)" +
                " VALUES (?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql, Episode,Lesson,Lesson_Code,Teacher,Classroom,Classroom_Number,Day,LessonTime);
    }
}
