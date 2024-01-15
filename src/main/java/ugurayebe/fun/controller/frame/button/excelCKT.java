package ugurayebe.fun.controller.frame.button;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ugurayebe.fun.controller.program.node.nodeAcademic;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static ugurayebe.fun.controller.program.node.nodeAcademic.FinishNodeAcademic;
import static ugurayebe.fun.database.config.jdbcTemplate;

public class excelCKT {
    // Özel Çıktı Al butonu oluşturuluyor
    public static JButton excelCKT(String frameType, String frameName) {
        JButton button = new JButton("Özel Cıktı Al");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                // Burada amacım FrameName (username) - Öğretmenin Ders Programı veya
                // Bilgisayar 1 - Bölümünün ders programı olduğu için
                // Sadece Baş kısmını alıyorum...
                String[] Slot = frameName.split(" - ");
                String columnData = Slot[0];
                String columnName = " ";

                // Şimdi burada Öğretmenin mi Bölümün mü bunu belirtiyorum ve Columname'mi seciyorum..
                if (frameType.equals("academic_program_episode")) {
                    String episodeSqlName = "SELECT id FROM Episode WHERE Name = ?";
                    columnData = jdbcTemplate.queryForObject(episodeSqlName, Integer.class, columnData) + " Ders Programı";
                    columnName = "Episode";

                } else {
                    String episodeSqlUsername = "SELECT id FROM Teacher WHERE Username = ?";
                    columnData = jdbcTemplate.queryForObject(episodeSqlUsername, Integer.class, columnData) + " Ders Programı";
                    columnName = "Teacher";
                }

                // Academic Nodemi cağiriyorumm... ve tüm verileri ekliyorum...
                nodeAcademic nodeAcadem = new nodeAcademic();
                nodeAcadem.main(columnName, columnData);


                try {
                    // Dosya seçici oluşturuluyor
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Save Excel File");
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx");
                    fileChooser.setFileFilter(filter);

                    // Kullanıcıdan kayıt konumunu seçmesini iste
                    int userSelection = fileChooser.showSaveDialog(null);

                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        // Seçilen dosyanın yolu belirleniyor
                        java.io.File fileToSave = fileChooser.getSelectedFile();

                        // Dosya uzantısı .xlsx değilse ekleniyor
                        if (!fileToSave.getAbsolutePath().toLowerCase().endsWith(".xlsx")) {
                            fileToSave = new java.io.File(fileToSave.getAbsolutePath() + ".xlsx");
                        }

                        // Excel dosyasını yükleyin
                        FileInputStream fileInputStream = new FileInputStream("src/main/resources/taslak.xlsx");
                        Workbook workbook = new XSSFWorkbook(fileInputStream);

                        // İlk sayfa (worksheet) seçin
                        Sheet sheet = workbook.getSheetAt(0);

                        // Ders sayısını al
                        String lessonCountQuery = "SELECT COUNT(*) FROM times WHERE type = ?";
                        int lessonCount = jdbcTemplate.queryForObject(lessonCountQuery, Integer.class, "Ders");

                        String lessonStart = "SELECT Start FROM times WHERE type = 'Ders' AND Number = ?";
                        String lessonFinish = "SELECT Finish FROM times WHERE type = 'Ders' AND Number = ?";


                        for (int i = 1; i <= lessonCount; i++) {
                            // {lesson1} olan placeholdersi 1. Ders olarak kayıt et
                            replaceTextInCells(sheet, "{lesson" + i + "}", i + ". Ders");

                            int startLesson = jdbcTemplate.queryForObject(lessonStart, Integer.class, i);
                            int finishLesson = jdbcTemplate.queryForObject(lessonFinish, Integer.class, i);

                            // {lesson1_hourse} olan placeholdersi 9:0 | 9:45  kayıt et
                            replaceTextInCells(sheet, "{lesson" + i + "_hourse}",
                                    returnClock(startLesson) + " | " + returnClock(finishLesson));

                        }

                        // Nodemizi en sona alıyoruz...
                        nodeAcademic active = FinishNodeAcademic;


                        while (active != null) { // Node bitene kadar dönecek döngümüz
                            // Eğer lessonCode'de "/" işareti varsa bu bölüme ayrılmış demektir.
                            if (((String) active.lessonCode).contains("/")) {
                                // ve bunu splint ile ayırıyoruz mesela Bl-50/2 ise bu 2. şube kayıt edilecek demektir...
                                String[] parcalar = ((String) active.lessonCode).split("/");
                                // Gerekli placeholdersleri güncelliyoruz...
                                replaceTextInCells(sheet,"{lesson_" + active.lessonTime + "." + parcalar[1] + "_lesson_" + active.day + "}",
                                        active.lesson + " " + active.lessonCode);
                                replaceTextInCells(sheet,"{lesson_" + active.lessonTime + "." + parcalar[1] + "_place_" + active.day + "}",
                                        active.classroom + " " + active.classroomNumber);
                                replaceTextInCells(sheet,"{lesson_" + active.lessonTime + "." + parcalar[1] + "_teacher_" + active.day + "}",
                                        active.teacher + " ");
                            } else {
                                // Eğer yoksa bu ortak derstir ve ortak dersleri tam olarak yazıyoruz 1 ve 2. şube aynı düzenle...
                                replaceTextInCells(sheet,"{lesson_" + active.lessonTime + ".1_lesson_" + active.day + "}",
                                        active.lesson + " " + active.lessonCode);
                                replaceTextInCells(sheet,"{lesson_" + active.lessonTime + ".2_lesson_" + active.day + "}",
                                        active.lesson + " " + active.lessonCode);
                                replaceTextInCells(sheet,"{lesson_" + active.lessonTime + ".1_place_" + active.day + "}",
                                        active.classroom + " " + active.classroomNumber);
                                replaceTextInCells(sheet,"{lesson_" + active.lessonTime + ".2_place_" + active.day + "}",
                                        active.classroom + " " + active.classroomNumber);
                                replaceTextInCells(sheet,"{lesson_" + active.lessonTime + ".1_teacher_" + active.day + "}",
                                        active.teacher + " ");
                                replaceTextInCells(sheet,"{lesson_" + active.lessonTime + ".2_teacher_" + active.day + "}",
                                        active.teacher + " ");
                            }
                            // Aktivi bir öncekine al... (Sondan başa gidiyoruz)
                            active = active.last;
                        }

                        // Tablo ismini Slottaki 0. Öğe ile değiştir.
                        replaceTextInCells(sheet, "{tablo_name}", Slot[0]);

                        // Koşeli paranter olan tüm stünleri sil..
                        deleteCellsStartingWith(sheet, "{");


                        // Değişiklikleri kaydedin
                        FileOutputStream fileOutputStream = new FileOutputStream(fileToSave);
                        workbook.write(fileOutputStream);

                        // Kapatın
                        fileInputStream.close();
                        fileOutputStream.close();
                        workbook.close();

                        System.out.println("Değişiklikler başarıyla yapıldı. Dosya: " + fileToSave.getAbsolutePath());
                    } else {
                        System.out.println("İptal edildi.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        return button;
    }

    // Buradan dakikayi saat dakika cinsine dönüştürüyoruz..
    public static String returnClock(int minutes) {
        int clock = minutes / 60;
        int minu = minutes % 60;
        return clock + ":" + minu;
    }



    /**
     * Excel sayfasındaki hücrelerde belirli bir metni arar ve bulduğu her hücrede bu metni başka bir metinle değiştirir.
     *
     * @param sheet           Değiştirilecek Excel sayfası
     * @param searchText      Değiştirilecek metni aramak için kullanılacak metin
     * @param replacementText Değiştirilecek metni yerine kullanılacak yeni metin
     */
    private static void replaceTextInCells(Sheet sheet, String searchText, String replacementText) {
        // Her satır için döngü
        for (Row row : sheet) {
            // Her hücre için döngü
            for (Cell cell : row) {
                // Hücre tipi STRING ise devam et
                if (cell.getCellType() == CellType.STRING) {
                    // Hücredeki metni al
                    String cellValue = cell.getStringCellValue();
                    // Eğer hücrede aranan metin varsa
                    if (cellValue.contains(searchText)) {
                        // Hücredeki metni değiştir ve yeni metin ile güncelle
                        cell.setCellValue(cellValue.replace(searchText, replacementText));
                    }
                }
            }
        }
    }



    /**
     * Excel sayfasındaki belirli bir önekle başlayan hücreleri siler.
     *
     * @param sheet  Silme işlemi yapılacak Excel sayfası
     * @param prefix Silinecek hücrelerin öneki
     */

    private static void deleteCellsStartingWith(Sheet sheet, String prefix) {
        // Her satır için döngü
        for (Row row : sheet) {
            // Her hücre için döngü
            for (Cell cell : row) {
                // Hücre tipi STRING ise devam et
                if (cell.getCellType() == CellType.STRING) {
                    // Hücredeki metni al
                    String cellValue = cell.getStringCellValue();
                    // Eğer hücredeki metin belirtilen önekle başlıyorsa
                    if (cellValue.startsWith(prefix)) {
                        // Hücre içeriğini sil
                        cell.setCellValue("");
                    }
                }
            }
        }
    }
}


