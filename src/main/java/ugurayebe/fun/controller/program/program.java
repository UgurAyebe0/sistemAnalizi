package ugurayebe.fun.controller.program;

import ugurayebe.fun.controller.program.node.availablesEpisode;
import ugurayebe.fun.controller.program.node.availablesRoom;
import ugurayebe.fun.controller.program.node.availablesTeacher;
import ugurayebe.fun.controller.program.node.nodeLesson;

import static ugurayebe.fun.controller.program.start.starlend;
import static ugurayebe.fun.database.config.jdbcTemplate;

public class program {
    public static void main(){
        // Academic programı sıfırlıyoruz
        String sql = "delete from academic_program";
        jdbcTemplate.execute(sql);
        // Öğretmen müsaitlikleri güncelle
        availablesTeacher teacher = new availablesTeacher();
        teacher.main();
        // Classroom'ları müsaitliği ekle
        availablesRoom room = new availablesRoom();
        room.main();
        // Bölümleri'lerin müsaitliğini ekle
        availablesEpisode episode = new availablesEpisode();
        episode.main();
        // Lesson bilgilerini al
        nodeLesson lesson = new nodeLesson();
        lesson.main();
        // Başlaa....
        starlend();
    }
}
