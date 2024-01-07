package ugurayebe.fun.controller.program.data;

import ugurayebe.fun.controller.program.node.availablesEpisode;
import ugurayebe.fun.controller.program.node.availablesRoom;
import ugurayebe.fun.controller.program.node.availablesTeacher;

import java.util.ArrayList;

import static ugurayebe.fun.controller.program.node.availablesEpisode.FinishEpisodeNode;
import static ugurayebe.fun.controller.program.node.availablesRoom.FinishRoomNode;
import static ugurayebe.fun.controller.program.node.availablesTeacher.FinishTeacherNode;

public class dataSet {

    public static void setNodeTeacher(Object Teacher, ArrayList removeDays) {
        availablesTeacher active = FinishTeacherNode;
        while (!active.Teacher.equals(Teacher)) {
            active = active.last;
        }
        active.Days.removeAll(removeDays);
    }

    public static void setNodeEpisode(Object Episode, ArrayList removeDays) {
        availablesEpisode active = FinishEpisodeNode;
        while (!active.Episode.equals(Episode)) {
            active = active.last;
        }
        active.Days.removeAll(removeDays);
    }

    public static void setNodeClassroom(Object Classroom, int Type, ArrayList removeDays) {
        availablesRoom active = FinishRoomNode;

        while (!(active.Room.equals(Classroom) && active.Custom == Type)) {
            active = active.last;
        }
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
