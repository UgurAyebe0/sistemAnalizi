package ugurayebe.fun.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class ExcelReplaceText {

    public static void main(String[] args) {
        try {

            System.out.println(1);
            // Excel dosyasını yükleyin
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/deneme.xlsx");
            Workbook workbook = new XSSFWorkbook(fileInputStream);

            // İlk sayfa (worksheet) seçin
            Sheet sheet = workbook.getSheetAt(0);

            String sqlCode = "select Lesson_Code from academic_program where episode = '3'";
            ArrayList arrayListLessonCode = (ArrayList) jdbcTemplate.queryForList(sqlCode, String.class);

            String sqlLesson = "select Lessons from academic_program where episode = '3'";
            ArrayList arrayListLesson = (ArrayList) jdbcTemplate.queryForList(sqlLesson, String.class);
            System.out.println(1);

            ArrayList Lesson = new ArrayList();
            String sqlLessonName = "select Name from lesson where id = ?";

            for (int i = 0; i < arrayListLessonCode.size(); i++) {
                Lesson.add(arrayListLessonCode.get(i) + " " + jdbcTemplate.queryForObject(sqlLessonName, String.class, arrayListLesson.get(i)));
            }
            for (Object a : Lesson) {
                System.out.println(a);
            }


            // Tüm hücreleri kontrol edin ve {deneme} olanları değiştirin
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.STRING) {
                        String cellValue = cell.getStringCellValue();
                        if (cellValue.contains("{lesson2}")) {
                            cell.setCellValue(cellValue.replace("{lesson2}", "ali"));
                        }
                    }
                }
            }

            // Değişiklikleri kaydedin
            FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/deneme_modified.xlsx");
            workbook.write(fileOutputStream);

            // Kapatın
            fileInputStream.close();
            fileOutputStream.close();
            workbook.close();

            System.out.println("Değişiklikler başarıyla yapıldı.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
