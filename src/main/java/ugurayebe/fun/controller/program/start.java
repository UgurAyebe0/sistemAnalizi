package ugurayebe.fun.controller.program;

import ugurayebe.fun.controller.program.node.nodeLesson;
import ugurayebe.fun.listener.showMessage;

import java.util.ArrayList;

import static ugurayebe.fun.listener.alert.notTeacher.teacherAlert;
import static ugurayebe.fun.controller.program.branch2Availables.getTeacherAvailables2;
import static ugurayebe.fun.controller.program.data.dataGet.*;
import static ugurayebe.fun.controller.program.data.dataSave.sendAcademicProgram;
import static ugurayebe.fun.controller.program.data.dataSet.*;
import static ugurayebe.fun.controller.program.node.nodeLesson.FinishLessonNode;
import static ugurayebe.fun.controller.program.branch1Availables.*;
import static ugurayebe.fun.database.config.jdbcTemplate;

public class start {
    public static void starlend() {

        // Lesson bilgileir alıyoruz
        nodeLesson lesson = new nodeLesson();
        lesson.main();

        // En son node alıyoruz
        nodeLesson active = FinishLessonNode.last;

        // Node bitene kadar calıştırıyoruz
        while (active != null) {

            // Öğretmen ve müsaitlik için bir Arrylist oluşturuyoruz
            ArrayList<String> teacherDay = new ArrayList<>();
            ArrayList<String> episodeDay = new ArrayList<>();
            try {
                // Buradan bilgileri cekiyoruz.....
                teacherDay = getNodeTeacher(active.Teacher.get(0));
                episodeDay = getNodeEpisode(active.Episode.get(0));
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                // Eğer hata varsa... hata yazdırıyoruz...
                if (teacherDay.size() == 0) {
                    showMessage.main("Lütfen " + active.Lesson + " Dersine öğretmen atayın.");
                } else if (episodeDay.size() == 0) {
                    showMessage.main("Lütfen " + active.Lesson + " Dersine bölüm atayın.");
                }
            }


            // Öğretmenin müsait olduğu kontrol edilen bir fonksiyon çağrılır.
            // Eğer öğretmen müsait değilse, başka bir müsait öğretmen bulma işlemleri gerçekleştirilir.
            String Timec = (String) active.Time; // Dersin saat bilgisini bir stringe çevirir.
            int Time = Integer.parseInt(Timec); // String olarak alınan saat bilgisini integer'a dönüştürür.
            // Boostrap hatasından dolayi ilk string sonra int

            // controlTeacher fonksiyonu ile öğretmenin müsait olup olmadığı kontrol edilir.
            // Eğer öğretmen müsait değilse, başka bir müsait öğretmen bulunması için işlemler yapılır.
            if (!controlTeacher(teacherDay, episodeDay, Time)) {
                // Tüm öğretmen ID'lerini içeren bir liste alınır.
                String sql = "SELECT id FROM Teacher";
                ArrayList<Integer> teacherIds = (ArrayList<Integer>) jdbcTemplate.queryForList(sql, Integer.class);
                // Mevcut öğretmen (active.Teacher) listeden çıkarılır.
                teacherIds.remove(active.Teacher);
                // Müsait öğretmeni bulmak için teacherAlert fonksiyonu çağrılır.
                // Bu fonksiyon, müsait öğretmeni bulana kadar işlemi tekrarlar.
                active.Teacher.set(0, teacherAlert(teacherIds, active.Lesson));
            }

            // Eğer Branch 1 ise bölünmeden...
            if (active.Branch.equals("1")) {

                // Müsaitlikleri al Arrylist sonunda classroom var.
                ArrayList availablesDay =
                        getTeacherAvailables(getNodeTeacher(active.Teacher.get(0)),  // Aktif öğretmenin düğümünü alır.
                        getNodeEpisode(active.Episode.get(0)), // Aktif bölümün düğümünü alır.
                                Integer.parseInt(active.Time.toString()), // Aktif dersin saati
                                active.Classroom, // Aktif dersin sınıf bilgisi
                                active.Lesson); // Aktif dersin adı

                String liste1 = (String) availablesDay.get(0);
                String[] parts = liste1.split(" ");

                while (parts.length != 2) { // ufak bir kontrol... eğer atama olmadıysa olana kadar tekrar yaptır.
                    availablesDay = getTeacherAvailables(getNodeTeacher(active.Teacher.get(0)),
                            getNodeEpisode(active.Episode.get(0)), Integer.parseInt(active.Time.toString()),
                                    active.Classroom, active.Lesson);
                }

                // Teacher ve episode müsait günü alıyoruz ve günleri siliyoruz.
                setNodeTeacher(active.Teacher.get(0), availablesDay);
                setNodeEpisode(active.Episode.get(0), availablesDay);

                // Classroom bilgileri alıyoruz malum en sonda Arrylistin...
                liste1 = (String) availablesDay.get(availablesDay.size() - 1);
                parts = liste1.split(" ");
                String classroom = parts[0];
                String stringCustom = parts[1];
                int Custom = Integer.parseInt(stringCustom);
                int Classroom = Integer.parseInt(classroom);
                setNodeClassroom(Classroom, Custom, availablesDay);

                // Kayıt işlemini Nodeden ve kontrollerden aldiğimiz bilgileri alıyoruz..
                for (int i = 0; i < Integer.parseInt(active.Time.toString()); i++) {
                    String day = (String) availablesDay.get(i);
                    String[] part = day.split(" ");
                    String Day = part[0];
                    String lessonTime = part[1];
                    sendAcademicProgram(active.Episode.get(0), active.lessonId, active.Code,
                            active.Teacher.get(0), Classroom, Custom, Day, lessonTime);
                }
            } else {
                if (active.Teacher.size() == 1) {
                    // 2 döngü kullanarak müsait günleri işle
                    for (int j = 1; j <= 2; j++) {
                        // Öğretmenin, bölümün ve gün sayısının dikkate alındığı müsait günleri al
                        ArrayList availablesDay = getTeacherAvailables2(getNodeTeacher(active.Teacher.get(0)),
                                getNodeEpisode(active.Episode.get(0)), Integer.parseInt(active.Time.toString()),
                                active.Classroom, active.Lesson, j);

                        // Müsait günü öğretmenden siliyoruz.
                        setNodeTeacher(active.Teacher.get(0), availablesDay);

                        // Müsait günü bölümden siliyoruz.
                        setNodeEpisode2(active.Episode.get(0), availablesDay, String.valueOf(j));

                        // Müsait günü Derslikten siliyoruz.
                        String liste1 = (String) availablesDay.get(availablesDay.size() - 1);
                        String[] parts = liste1.split(" ");
                        String classroom = parts[0];
                        String stringCustom = parts[1];
                        int Custom = Integer.parseInt(stringCustom);
                        int Classroom = Integer.parseInt(classroom);

                        // Gün formatını düzenle
                        for (int i = 0; i < availablesDay.size() - 1; i++) {
                            String liste2 = (String) availablesDay.get(i);
                            String[] part = liste2.split(" ");
                            String day = part[0];
                            String custom = part[1];
                            availablesDay.set(i, day + " " + custom);
                        }

                        // Düğümdeki günleri belirtilen günlerden sil
                        setNodeClassroom(Classroom, Custom, availablesDay);

                        // Kayıt et
                        for (int i = 0; i < Integer.parseInt(active.Time.toString()); i++) {
                            String day = (String) availablesDay.get(i);
                            String[] part = day.split(" ");
                            String Day = part[0];
                            String lessonTime = part[1];
                            sendAcademicProgram(active.Episode.get(0), active.lessonId, active.Code + "/" + j,
                                    active.Teacher.get(0), Classroom, Custom, Day, lessonTime);
                        }
                    }
                } else {
                    for (int j = 1; j <= 2; j++) {

                        // Müsait günü ve Classroom alıyoruz. Değişen şey teacher.get 0 değil j-1 alacağız
                        ArrayList availablesDay = getTeacherAvailables2(getNodeTeacher(active.Teacher.get(j - 1)),
                                getNodeEpisode(active.Episode.get(0)), Integer.parseInt(active.Time.toString()),
                                        active.Classroom, active.Lesson, j);

                        // Müsait günü öğretmenden siliyoruz.
                        setNodeTeacher(active.Teacher.get(j-1), availablesDay);

                        // Müsait günü bölümden siliyoruz.
                        setNodeEpisode2(active.Episode.get(0), availablesDay, String.valueOf(j));

                        // Müsait günü Derslikten siliyoruz.
                        String liste1 = (String) availablesDay.get(availablesDay.size() - 1);
                        String[] parts = liste1.split(" ");
                        String classroom = parts[0];
                        String stringCustom = parts[1];
                        int Custom = Integer.parseInt(stringCustom);
                        int Classroom = Integer.parseInt(classroom);

                        // Gün formatını düzenle
                        for (int i = 0; i < availablesDay.size() - 1; i++) {
                            String liste2 = (String) availablesDay.get(i);
                            String[] part = liste2.split(" ");
                            String day = part[0];
                            String custom = part[1];
                            availablesDay.set(i, day + " " + custom);
                        }

                        // Düğümdeki günleri belirtilen günlerden sil
                        setNodeClassroom(Classroom, Custom, availablesDay);

                        for (int i = 0; i < Integer.parseInt(active.Time.toString()); i++) {
                            String day = (String) availablesDay.get(i);
                            String[] part = day.split(" ");
                            String Day = part[0];
                            String lessonTime = part[1];
                            sendAcademicProgram(active.Episode.get(0), active.lessonId, active.Code + "/"
                                    + j, active.Teacher.get(j - 1), Classroom, Custom, Day, lessonTime);
                        }
                    }
                }
            }

            // düğümü ilerlet..
            active = active.last;

        }


    }


    public static boolean controlTeacher(ArrayList<String> teacherDay, ArrayList<String> episodeDay, int lessonTime) {

        for (int i = 0; i < teacherDay.size(); i++) {
            String[] Slot = teacherDay.get(i).split(" ");
            String Day = Slot[0];
            int Time = Integer.parseInt(Slot[1]);


            int sayaç = 0;

            for (int j = 0; j < lessonTime; j++) {
                if (teacherDay.contains(Day + " " + (Time + j))) {
                    sayaç++;
                }
            }

            if (sayaç == lessonTime) {

                int sayaç2 = 0;

                for (int j = 0; j < lessonTime; j++) {
                    if (episodeDay.contains(Day + " " + (Time + j))) {
                        sayaç2++;
                    }
                }
                if (sayaç2 == lessonTime) {

                    return true;
                }
            }
        }
        return false;
    }


}
