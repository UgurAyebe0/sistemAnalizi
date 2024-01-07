package ugurayebe.fun.controller.program.node;

import java.util.ArrayList;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class nodeLesson {
    public Object Lesson;
    public Object lessonId;
    public Object Type;
    public Object Classroom;
    public Object Capacity;
    public Object Custom;
    public Object Code;
    public Object Time;
    public Object Student;
    public Object Branch;
    public ArrayList Episode;
    public ArrayList Teacher;
    public nodeLesson next;
    public nodeLesson last;

    public nodeLesson(Object Lesson, Object lessonId, Object Classroom, Object Type, Object Capacity, Object Custom, Object Code, Object Time, Object Student, Object Branch, ArrayList Episode, ArrayList Teacher) {
        this.Lesson = Lesson;
        this.lessonId = lessonId;
        this.Classroom = Classroom;
        this.Type = Type;
        this.Capacity = Capacity;
        this.Custom = Custom;
        this.Code = Code;
        this.Time = Time;
        this.Student = Student;
        this.Branch = Branch;
        this.Episode = Episode;
        this.Teacher = Teacher;
        this.next = null;
        this.last = null;
    }


    public static nodeLesson FinishLessonNode = new nodeLesson(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", new ArrayList(), new ArrayList());

    public nodeLesson() {
        FinishLessonNode.last = null;
        FinishLessonNode.next = null;
    }

    public static void addNodeList(Object Lesson, Object lessonId, Object Classroom, Object Name, Object Capacity, Object Custom, Object Code, Object Time, Object Student, Object Branch, ArrayList Episode, ArrayList Teacher) {
        nodeLesson newNode = new nodeLesson(Lesson, lessonId, Classroom, Name, Capacity, Custom, Code, Time, Student, Branch, Episode, Teacher);

        newNode.last = FinishLessonNode.last;
        FinishLessonNode.last = newNode;
        newNode.next = FinishLessonNode;
    }

    public static void listing() {
        nodeLesson active = FinishLessonNode;
        while (active != null) {
            System.out.println("-*-*-*-*-*-*-*-*-");
            System.out.println("Aktif Ders: " + active.Lesson + " (" + active.lessonId + ")");
            System.out.println("Code: " + active.Code);
            System.out.println("Classroom: " + active.Classroom);
            System.out.println("Type: " + active.Type);
            System.out.println("Capacity: " + active.Capacity);
            System.out.println("Custom: " + active.Custom);
            System.out.println("Time: " + active.Time);
            System.out.println("Student: " + active.Student);
            System.out.println("Branch: " + active.Branch);
            System.out.println("Episode: " + active.Episode);
            System.out.println("Teacher: " + active.Teacher);
            System.out.println("-*-*-*-*-*-*-*-*-");
            active = active.last;
        }
    }


    public static void main() {

        String sql = "SELECT id FROM lesson";
        ArrayList<Integer> lessons = (ArrayList<Integer>) jdbcTemplate.queryForList(sql, Integer.class);


        String lessonClassroom = "SELECT Classroom FROM lesson WHERE id = ?";
        String lessonCode = "SELECT Code FROM lesson WHERE id = ?";
        String lessonName = "SELECT Name FROM lesson WHERE id = ?";
        String lessonTime = "SELECT Time FROM lesson WHERE id = ?";
        String lessonStudent = "SELECT Student FROM lesson WHERE id = ?";
        String lessonBranch = "SELECT Branch FROM lesson WHERE id = ?";


        String lessonTeacher = "SELECT Episode FROM episodecourses WHERE lesson = ?";
        String lessonEpisode = "SELECT Teacher FROM teachercourses WHERE lesson = ?";

        String classroomCapacity = "SELECT Capacity FROM classroom WHERE id = ?";
        String classroomCustom = "SELECT Custom FROM classroom WHERE id = ?";
        String classroomName = "SELECT Type FROM classroom WHERE id = ?";


        for (Object id : lessons) {


            String Classroom = jdbcTemplate.queryForObject(lessonClassroom, String.class, id);
            String Code = jdbcTemplate.queryForObject(lessonCode, String.class, id);
            String Lesson = jdbcTemplate.queryForObject(lessonName, String.class, id);
            String Time = jdbcTemplate.queryForObject(lessonTime, String.class, id);
            String Student = jdbcTemplate.queryForObject(lessonStudent, String.class, id);
            String Branch = jdbcTemplate.queryForObject(lessonBranch, String.class, id);


            String Capacity = jdbcTemplate.queryForObject(classroomCapacity, String.class, Classroom);
            String Custom = jdbcTemplate.queryForObject(classroomCustom, String.class, Classroom);
            String Type = jdbcTemplate.queryForObject(classroomName, String.class, Classroom);


            ArrayList<Integer> episode = (ArrayList<Integer>) jdbcTemplate.queryForList(lessonTeacher, Integer.class, id);
            ArrayList<Integer> teacher = (ArrayList<Integer>) jdbcTemplate.queryForList(lessonEpisode, Integer.class, id);

            addNodeList(Lesson,id, Classroom, Type, Capacity, Custom, Code, Time, Student, Branch, episode, teacher);
        }
    }


}
