package ugurayebe.fun.controller.program.node;

import java.util.ArrayList;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class nodeLesson {
    // Node sınıfı için değişkenler
    public Object Lesson;  public Object lessonId;
    public Object Type;  public Object Classroom;
    public Object Capacity; public Object Custom;
    public Object Code;  public Object Time;
    public Object Student; public Object Branch;
    public ArrayList Episode;  public ArrayList Teacher;
    public nodeLesson next; public nodeLesson last;

    // Yapıcı metod
    public nodeLesson(Object Lesson, Object lessonId, Object Classroom, Object Type, Object Capacity,
                      Object Custom, Object Code, Object Time, Object Student, Object Branch, ArrayList Episode, ArrayList Teacher) {
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

    // Boş bir bitiş node'u oluşturuluyor
    public static nodeLesson FinishLessonNode = new nodeLesson(" ", " ", " ",
            " ", " ", " ", " ", " ", " ", " ",
                    new ArrayList(), new ArrayList());

    // Boş bir bitiş node'u oluşturuluyor
    public nodeLesson() {
        FinishLessonNode.last = null;
        FinishLessonNode.next = null;
    }

    // Yeni bir node ekleyen metod
    public static void addNodeList(Object Lesson, Object lessonId, Object Classroom, Object Name,
                                   Object Capacity, Object Custom, Object Code, Object Time, Object Student,
                                   Object Branch, ArrayList Episode, ArrayList Teacher) {
        nodeLesson newNode = new nodeLesson(Lesson, lessonId, Classroom, Name, Capacity, Custom, Code, Time, Student, Branch, Episode, Teacher);

        newNode.last = FinishLessonNode.last;
        FinishLessonNode.last = newNode;
        newNode.next = FinishLessonNode;
    }


    // Ana metod - Ders bilgilerini çekip linked list'e ekler
    public static void main() {

        // Tüm derslerin ID'lerini al
        String sql = "SELECT id FROM lesson";
        ArrayList<Integer> lessons = (ArrayList<Integer>) jdbcTemplate.queryForList(sql, Integer.class);

        // Dersle ilgili bilgileri çekmesi için SQL
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

        // Default
        String Classroom = " ";
        String Code = " ";
        String Lesson = " ";
        String Time = " ";
        String Student = " ";
        String Branch = " ";
        String Capacity = " ";
        String Custom = " ";
        String Type = " ";

        ArrayList<Integer> episode = new ArrayList<>();
        ArrayList<Integer> teacher = new ArrayList<>();

        Object ide = 1;

        // Ders bilgilerini çekip linked list'e ekle
        for (Object id : lessons) {

            // Dersle ilgili bilgileri çek
             Classroom = jdbcTemplate.queryForObject(lessonClassroom, String.class, id);
             Code = jdbcTemplate.queryForObject(lessonCode, String.class, id);
             Lesson = jdbcTemplate.queryForObject(lessonName, String.class, id);
             Time = jdbcTemplate.queryForObject(lessonTime, String.class, id);
             Student = jdbcTemplate.queryForObject(lessonStudent, String.class, id);
             Branch = jdbcTemplate.queryForObject(lessonBranch, String.class, id);


             Capacity = jdbcTemplate.queryForObject(classroomCapacity, String.class, Classroom);
             Custom = jdbcTemplate.queryForObject(classroomCustom, String.class, Classroom);
             Type = jdbcTemplate.queryForObject(classroomName, String.class, Classroom);

             episode = (ArrayList<Integer>) jdbcTemplate.queryForList(lessonTeacher, Integer.class, id);
             teacher = (ArrayList<Integer>) jdbcTemplate.queryForList(lessonEpisode, Integer.class, id);

            ide =id;
            addNodeList(Lesson,id, Classroom, Type, Capacity, Custom, Code, Time, Student, Branch, episode, teacher);
        }
        addNodeList(Lesson,ide, Classroom, Type, Capacity, Custom, Code, Time, Student, Branch, episode, teacher);

    }


}
