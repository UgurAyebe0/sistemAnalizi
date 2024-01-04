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
                return arrayListe();
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
    public static ArrayList arrayListe() {
        String sql = "SELECT code,name from Lesson";
        ArrayList liste = returnListe.main(sql);
        ArrayList returnlen = new ArrayList();
        for (int i = 0; i < liste.size(); i = i + 2) {
            returnlen.add("(" + liste.get(i) + ") " + liste.get(i+1));
        }
        return returnlen;
    }
    public static ArrayList arrayListDays() {
        String sql = "SELECT Day from Days";
        return returnListe.main(sql);
    }
}
