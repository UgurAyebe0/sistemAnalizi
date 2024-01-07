package ugurayebe.fun.controller.program;

import ugurayebe.fun.controller.program.node.availablesEpisode;
import ugurayebe.fun.controller.program.node.availablesRoom;
import ugurayebe.fun.controller.program.node.availablesTeacher;
import ugurayebe.fun.controller.program.node.nodeLesson;

import static ugurayebe.fun.controller.program.start.starlend;
import static ugurayebe.fun.database.config.jdbcTemplate;

public class program {

    public static void main(){

        String sql = "delete from academic_program";
        jdbcTemplate.execute(sql);

        availablesTeacher teacher = new availablesTeacher();
        teacher.main();


        availablesRoom room = new availablesRoom();
        room.main();

        availablesEpisode episode = new availablesEpisode();
        episode.main();


        nodeLesson lesson = new nodeLesson();
        lesson.main();


        starlend();


    }
}
