package ugurayebe.fun.controller.program.node;

import java.util.ArrayList;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class availablesRoom {
    public Object Room;
    public int Custom;
    public int Capacity;
    public ArrayList Days;
    public availablesRoom next;
    public availablesRoom last;

    public availablesRoom(Object Room, int Custom, int Capacity, ArrayList Days) {
        this.Room = Room;
        this.Custom = Custom;
        this.Days = Days;
        this.Capacity = Capacity;
        this.next = null;
        this.last = null;
    }

    public static availablesRoom FinishRoomNode = new availablesRoom(" ", ' ', ' ', new ArrayList());

    public availablesRoom() {
        FinishRoomNode.last = null;
        FinishRoomNode.next = null;
    }

    public static void addNodeList(Object Room, int Custom, int Capacity,ArrayList Days) {
        availablesRoom newNode = new availablesRoom(Room, Custom,Capacity, Days);
        newNode.last = FinishRoomNode.last;
        FinishRoomNode.last = newNode;
        newNode.next = FinishRoomNode;
    }

    public static void listing() {
        availablesRoom active = FinishRoomNode;
        while (active != null) {
            System.out.println("-*-*-*-*-*-*-*-*-");
            System.out.println("ClassRoom: " + active.Room);
            System.out.println("Custom: " + active.Custom);
            System.out.println("Capacity: " + active.Capacity);
            System.out.println("Müsait Günler: " + active.Days);
            System.out.println("-*-*-*-*-*-*-*-*-");
            active = active.last;
        }
    }


    public static void main() {

        String sql = "SELECT id FROM classroom";
        ArrayList<Integer> classroomIds = (ArrayList<Integer>) jdbcTemplate.queryForList(sql, Integer.class);

        String lessonCountQuery = "SELECT COUNT(*) FROM times WHERE type = ?";
        int lessonCount = jdbcTemplate.queryForObject(lessonCountQuery, Integer.class, "Ders");

        String daySql = "SELECT Day FROM days";
        ArrayList schoolDay = (ArrayList) jdbcTemplate.queryForList(daySql, String.class);


        String classroomCapacity = "SELECT Capacity FROM classroom WHERE id = ?";

        for (Object id : classroomIds) {

            String customSql = "SELECT Custom FROM classroom Where id = ?";
            int custom = jdbcTemplate.queryForObject(customSql, Integer.class, id);
            int Capacity = jdbcTemplate.queryForObject(classroomCapacity, Integer.class, id);


            for (int j = 1; j <= custom; j++) {
                ArrayList sendActive = new ArrayList();
                for (Object Day : schoolDay) {
                    for (int i = 1; i <= lessonCount; i++) {
                        sendActive.add(Day + " " + i);
                    }
                }
                addNodeList(id, j,Capacity, sendActive);
            }
        }
    }
}
