package ugurayebe.fun.controller.program;

import java.util.ArrayList;

public class Node {
    public String lessons;
    public boolean status;
    public ArrayList teachers;
    public ArrayList episodes;
    public Node next;

    public Node (String lessons, boolean status, ArrayList teachers, ArrayList episodes){
        this.lessons = lessons;
        this.status = status;
        this.teachers = teachers;
        this.episodes = episodes;
        this.next = null;
    }


    public static Node firstNode = new Node(" ", false, new ArrayList(), new ArrayList<>());
    public static Node FinishNode = new Node(" ", false, new ArrayList(), new ArrayList<>());

    public Node() {
        firstNode.next = FinishNode;
        FinishNode.next = null;
    }

    public static void addNodeList(String lessons, ArrayList teachers, ArrayList episodes){
        Node newNode = new Node(lessons, true, teachers, episodes);
        newNode.next = FinishNode;
//        firstNode;


    }

}
