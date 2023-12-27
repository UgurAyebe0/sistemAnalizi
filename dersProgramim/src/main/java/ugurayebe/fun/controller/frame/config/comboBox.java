package ugurayebe.fun.controller.frame.config;

import ugurayebe.fun.database.returnListe;
import java.util.ArrayList;
import java.util.List;

public class comboBox {

    public static ArrayList genereted(String methodName) {
        switch (methodName) {
            case "Op":
                return arrayList();
            case "Title":
                return arrayList("Name", "Title");
            case "Teacher":
                return arrayList("Username", "Teacher");
            case "Episode":
                return arrayList("Name", "Episode");
            case "Classroom":
                return arrayList("Type", "Classroom");
            case "Lesson":
                return arrayList("Name", "Lesson");
            case "Day":
                return arrayListDays();
            default:
                return new ArrayList<>();
        }
    }
    public static ArrayList arrayList() {
        return new ArrayList<>(List.of("Tam Yetki", "Kısıtlı Yetki"));
    }
    public static ArrayList arrayList(String Data, String Table) {
        String sql = "SELECT " + Data + " from " + Table;
        return returnListe.main(sql);
    }
    public static ArrayList arrayListDays() {
        String sql = "SELECT Day from Days Where Confirmation = 1";
        return returnListe.main(sql);
    }
}
