package ugurayebe.fun.controller.program.node;

import java.util.ArrayList;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class availablesRoom {
    // Node sınıfı için değişkenler
    public Object Room;
    public int Custom;
    public int Capacity;
    public ArrayList Days;
    public availablesRoom next;
    public availablesRoom last;

    // Yapıcı metod
    public availablesRoom(Object Room, int Custom, int Capacity, ArrayList Days) {
        this.Room = Room;
        this.Custom = Custom;
        this.Days = Days;
        this.Capacity = Capacity;
        this.next = null;
        this.last = null;
    }
    // Boş bir bitiş node'u oluşturuluyor
    public static availablesRoom FinishRoomNode = new availablesRoom(" ", ' ', ' ', new ArrayList());
    // Boş bir bitiş node'u oluşturuluyor
    public availablesRoom() {
        FinishRoomNode.last = null;
        FinishRoomNode.next = null;
    }

    // Yeni bir node ekleyen metod
    public static void addNodeList(Object Room, int Custom, int Capacity,ArrayList Days) {
        availablesRoom newNode = new availablesRoom(Room, Custom,Capacity, Days);
        newNode.last = FinishRoomNode.last;
        FinishRoomNode.last = newNode;
        newNode.next = FinishRoomNode;
    }
    // Ana metod - Sınıfların kullanılabilirlik durumlarını oluşturur
    public static void main() {

        // Tüm sınıfların ID'lerini al
        String sql = "SELECT id FROM classroom";
        ArrayList<Integer> classroomIds = (ArrayList<Integer>) jdbcTemplate.queryForList(sql, Integer.class);

        // Ders programındaki ders sayısını al
        String lessonCountQuery = "SELECT COUNT(*) FROM times WHERE type = ?";
        int lessonCount = jdbcTemplate.queryForObject(lessonCountQuery, Integer.class, "Ders");

        // Tüm günleri al
        String daySql = "SELECT Day FROM days";
        ArrayList schoolDay = (ArrayList) jdbcTemplate.queryForList(daySql, String.class);



        for (Object id : classroomIds) {
            // Derslik, Labotuar bunların adetini al kaç adet var
            String customSql = "SELECT Custom FROM classroom Where id = ?";
            int custom = jdbcTemplate.queryForObject(customSql, Integer.class, id);
            // Her bir sınıf için günleri ve saatleri oluşturup linked list'e ekle
            String classroomCapacity = "SELECT Capacity FROM classroom WHERE id = ?";
            int Capacity = jdbcTemplate.queryForObject(classroomCapacity, Integer.class, id);


            // Müsaitlikleri ekleme...
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
