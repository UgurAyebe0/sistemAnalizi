package ugurayebe.fun.controller.program.node;

import java.util.ArrayList;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class availablesEpisode {

    // Node sınıfı için değişkenler
    public Object Episode;
    public ArrayList Days;
    public availablesEpisode next;
    public availablesEpisode last;

    // Yapıcı metod
    public availablesEpisode(Object Episode, ArrayList Days) {
        this.Episode = Episode;
        this.Days = Days;
        this.next = null;
        this.last = null;
    }

    // Boş bir bitiş node'u oluşturuluyor
    public static availablesEpisode FinishEpisodeNode = new availablesEpisode(" ", new ArrayList());
    public availablesEpisode() {
        FinishEpisodeNode.last = null;
        FinishEpisodeNode.next = null;
    }

    // Yeni bir node ekleyen metod
    public static void addNodeList(Object Episode, ArrayList Days) {
        availablesEpisode newNode = new availablesEpisode(Episode, Days);
        newNode.last = FinishEpisodeNode.last;
        FinishEpisodeNode.last = newNode;
        newNode.next = FinishEpisodeNode;
    }

    // Ana metod - Bölümü tamamen müsait olarak oluşturacak
    public static void main() {

        // Tüm Bölümlerin ID'lerini al
        String sql = "SELECT id FROM Episode";
        ArrayList<Integer> episodeIds = (ArrayList<Integer>) jdbcTemplate.queryForList(sql, Integer.class);

        // Ders sayısını al 8 ders gibi
        String lessonCountQuery = "SELECT COUNT(*) FROM times WHERE type = ?";
        int lessonCount = jdbcTemplate.queryForObject(lessonCountQuery, Integer.class, "Ders");

        // Günleri al
        String daySql = "SELECT Day FROM days";
        ArrayList schoolDay = (ArrayList) jdbcTemplate.queryForList(daySql, String.class);

        // Döngü ile Pazartesi 1 gibi kayıt et.
        for (Object id : episodeIds) {
            ArrayList sendActive = new ArrayList();
            for (Object Day : schoolDay) {
                for (int i = 1; i <= lessonCount; i++) {
                    sendActive.add(Day + " " + i);
                }
            }
            addNodeList(id, sendActive);
        }
    }
}

