package ugurayebe.fun.controller.program.node;

import java.util.ArrayList;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class availablesEpisode {
    public Object Episode;
    public ArrayList Days;
    public availablesEpisode next;
    public availablesEpisode last;

    public availablesEpisode(Object Episode, ArrayList Days) {
        this.Episode = Episode;
        this.Days = Days;
        this.next = null;
        this.last = null;
    }


    public static availablesEpisode FinishEpisodeNode = new availablesEpisode(" ", new ArrayList());

    public availablesEpisode() {
        FinishEpisodeNode.last = null;
        FinishEpisodeNode.next = null;
    }

    public static void addNodeList(Object Episode, ArrayList Days) {
        availablesEpisode newNode = new availablesEpisode(Episode, Days);
        newNode.last = FinishEpisodeNode.last;
        FinishEpisodeNode.last = newNode;
        newNode.next = FinishEpisodeNode;
    }

    public static void listing() {
        availablesEpisode active = FinishEpisodeNode;
        while (active != null) {
            System.out.println("-*-*-*-*-*-*-*-*-");
            System.out.println("Episode: " + active.Episode);
            System.out.println("Müsait Günler: " + active.Days);
            System.out.println("-*-*-*-*-*-*-*-*-");
            active = active.last;
        }
    }

    public static void main() {

        String sql = "SELECT id FROM Episode";
        ArrayList<Integer> episodeIds = (ArrayList<Integer>) jdbcTemplate.queryForList(sql, Integer.class);

        String lessonCountQuery = "SELECT COUNT(*) FROM times WHERE type = ?";
        int lessonCount = jdbcTemplate.queryForObject(lessonCountQuery, Integer.class, "Ders");

        String daySql = "SELECT Day FROM days";
        ArrayList schoolDay = (ArrayList) jdbcTemplate.queryForList(daySql, String.class);

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

