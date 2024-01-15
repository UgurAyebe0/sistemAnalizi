package ugurayebe.fun.controller.frame.config;

import ugurayebe.fun.database.returnListe;

import java.util.ArrayList;
import java.util.List;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class comboBox {

    // Field Name göre mesela JComboBoxOp olan fieldName op olarak buradan bize gönderecek.
    // ve bizde buna ait en uygun yapıyı Switch ile bulacağız...
    public static ArrayList genereted(String fieldName) {
        switch (fieldName) {
            case "Op":
                return permission();
            case "Branch":
                return branch();
            case "LessonTime":
                return lessonTime();
            case "Title":
                return arrayList("Name", "Title");
            case "Teacher":
                return arrayList("Username", "Teacher");
            case "Episode":
                return arrayList("Name", "Episode");
            case "Lessons":
                return arrayList("Name", "Lesson");
            case "Classroom":
                return arrayList("Type", "Classroom");
            case "Lesson":
                return lessonCode();
            case "Day":
                return daysName();
            default:
                return new ArrayList<>();
        }
    }

    // Buradan comboBox içerisini tam ve kısıtlı yetki olarak göndereceğiz.
    private static ArrayList permission() {
        return new ArrayList<>(List.of("Tam Yetki", "Kısıtlı Yetki"));
    }

    // Buradan comboBox içerisini Bölünneyecek ve Bölünecek olarak göndereceğiz.
    private static ArrayList branch() {
        return new ArrayList<>(List.of("Bölünecek", "Bölünneyecek"));
    }

    // Buradan zamana bakıp bilgi yollicaz 1 günde kaç adet ders olduğunu
    private static ArrayList<Integer> lessonTime() {
        String sql = "SELECT Number FROM times WHERE type = 'Ders'";
        return returnListe.main(sql);
    }

    // Buradan Sql'deki bilgileri alıp liste yapıp yollicaz.
    private static ArrayList arrayList(String Data, String Table) {
        String sql = "SELECT " + Data + " from " + Table;
        return returnListe.main(sql);
    }

    // Buradan (Bl-56) Muhasebe gibi bir veri kayıt edip comboBox ekleyeceğiz.
    private static ArrayList lessonCode() {
        String sql = "SELECT code,name from Lesson";
        ArrayList liste = returnListe.main(sql);
        ArrayList returnlen = new ArrayList();
        for (int i = 0; i < liste.size(); i = i + 2) {
            returnlen.add("(" + liste.get(i) + ") " + liste.get(i + 1));
        }
        return returnlen;
    }

    // buradan gün isimlerini ekleyeceğiz
    private static ArrayList daysName() {
        String sql = "SELECT Day from Days";
        return returnListe.main(sql);
    }
}
