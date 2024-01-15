package ugurayebe.fun.controller.program.data;

import ugurayebe.fun.controller.program.node.availablesEpisode;
import ugurayebe.fun.controller.program.node.availablesRoom;
import ugurayebe.fun.controller.program.node.availablesTeacher;

import java.util.ArrayList;

import static ugurayebe.fun.controller.program.node.availablesEpisode.FinishEpisodeNode;
import static ugurayebe.fun.controller.program.node.availablesRoom.FinishRoomNode;
import static ugurayebe.fun.controller.program.node.availablesTeacher.FinishTeacherNode;

public class dataSet {

    /**
     * Belirtilen öğretmenin müsait günlerinden belirtilen günleri çıkarır.
     *
     * @param Teacher      Günleri çıkarılacak öğretmen
     * @param removeDays   Çıkarılacak günlerin listesi
     */
    public static void setNodeTeacher(Object Teacher, ArrayList removeDays) {
        // Listenin başından başlayarak öğretmeni bulana kadar döngü yapar
        availablesTeacher active = FinishTeacherNode;
        while (!active.Teacher.equals(Teacher)) {
            active = active.last;
        }
        // Belirtilen günleri öğretmenin müsait günlerinden çıkarır
        active.Days.removeAll(removeDays);
    }

    /**
     * Belirtilen bölümün müsait günlerinden belirtilen günleri çıkarır.
     *
     * @param Episode      Günleri çıkarılacak bölüm
     * @param removeDays   Çıkarılacak günlerin listesi
     */
    public static void setNodeEpisode(Object Episode, ArrayList removeDays) {
        // Listenin başından başlayarak bölümü bulana kadar döngü yapar
        availablesEpisode active = FinishEpisodeNode;
        while (!active.Episode.equals(Episode)) {
            active = active.last;
        }
        // Belirtilen günleri bölümün müsait günlerinden çıkarır
        active.Days.removeAll(removeDays);
    }

    // Belirli bir sınıfın ve türün (type) olduğu günleri belirlenen günlerden silen metot
    public static void setNodeClassroom(Object Classroom, int Type, ArrayList removeDays) {
        availablesRoom active = FinishRoomNode;

        // Sınıfın ve türün bulunduğu düğümü ara
        while (!(active.Room.equals(Classroom) && active.Custom == Type)) {
            active = active.last;
        }
        // Bulunan düğümdeki günleri belirtilen günlerden sil
        active.Days.removeAll(removeDays);
    }


    public static void setNodeEpisode2(Object Episode, ArrayList removeDays, String renameDays) {
        availablesEpisode active = FinishEpisodeNode;
        while (!active.Episode.equals(Episode)) {
            active = active.last;
        }
        active.Days.removeAll(removeDays);


        for (int i = 0; i < removeDays.size(); i++) {
            removeDays.set(i,removeDays.get(i)+ " " +  (renameDays.trim().equals("1") ? 2 : 1));
        }
        active.Days.addAll(removeDays);


    }
}
