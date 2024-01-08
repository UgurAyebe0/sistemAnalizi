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
import static ugurayebe.fun.listener.veriables.frame;
import static ugurayebe.fun.view.opMenu.opPermission;

public class excelCKT {
    public static JButton excelCKT(String frameType, String frameName) {
        JButton button = new JButton("Özel Cıktı Al");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] Slot = frameName.split(" - ");

                String columnData = Slot[0];
                String columnName = " ";
                System.out.println("parcalar[0] " + Slot[0]);

                if (frameType.equals("academic_program_episode")) {
                    String episodeSqlName = "SELECT id FROM Episode WHERE Name = ?";
                    columnData = jdbcTemplate.queryForObject(episodeSqlName, Integer.class, columnData) + " Ders Programı";
                    columnName = "Episode";
                } else {
                    String episodeSqlUsername = "SELECT id FROM Teacher WHERE Username = ?";
                    columnData = jdbcTemplate.queryForObject(episodeSqlUsername, Integer.class, columnData) + " Ders Programı";
                    columnName = "Teacher";
                }

                nodeAcademic nodeAcadem = new nodeAcademic();
                nodeAcadem.main(columnName, columnData);


                try {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Save Excel File");
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx");
                    fileChooser.setFileFilter(filter);

                    int userSelection = fileChooser.showSaveDialog(null);

                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        java.io.File fileToSave = fileChooser.getSelectedFile();

                        if (!fileToSave.getAbsolutePath().toLowerCase().endsWith(".xlsx")) {
                            fileToSave = new java.io.File(fileToSave.getAbsolutePath() + ".xlsx");
                        }
                        // Excel dosyasını yükleyin
                        FileInputStream fileInputStream = new FileInputStream("src/main/resources/taslak.xlsx");
                        Workbook workbook = new XSSFWorkbook(fileInputStream);

                        // İlk sayfa (worksheet) seçin
                        Sheet sheet = workbook.getSheetAt(0);


                        String lessonCountQuery = "SELECT COUNT(*) FROM times WHERE type = ?";
                        int lessonCount = jdbcTemplate.queryForObject(lessonCountQuery, Integer.class, "Ders");

                        String lessonStart = "SELECT Start FROM times WHERE type = 'Ders' AND Number = ?";
                        String lessonFinish = "SELECT Finish FROM times WHERE type = 'Ders' AND Number = ?";

                        for (int i = 1; i <= lessonCount; i++) {
                            replaceTextInCells(sheet, "{lesson" + i + "}", i + ". Ders");

                            int startLesson = jdbcTemplate.queryForObject(lessonStart, Integer.class, i);
                            int finishLesson = jdbcTemplate.queryForObject(lessonFinish, Integer.class, i);

                            replaceTextInCells(sheet, "{lesson" + i + "_hourse}", returnClock(startLesson) + " | " + returnClock(finishLesson));

                        }
                        nodeAcademic active = FinishNodeAcademic;
                        System.out.println("AKTİF 1");
                        while (active != null) {
                            System.out.println("AKTİF 2");
                            if (((String) active.lessonCode).contains("/")) {

                                String[] parcalar = ((String) active.lessonCode).split("/");

                                replaceTextInCells(sheet, "{lesson_" + active.lessonTime + "." + parcalar[1] + "_lesson_" + active.day + "}", active.lesson + " " + active.lessonCode);
                                replaceTextInCells(sheet, "{lesson_" + active.lessonTime + "." + parcalar[1] + "_place_" + active.day + "}", active.classroom + " " + active.classroomNumber);
                                replaceTextInCells(sheet, "{lesson_" + active.lessonTime + "." + parcalar[1] + "_teacher_" + active.day + "}", active.teacher + " ");


                            } else {

                                System.out.println("{lesson_" + active.lessonTime + ".1_lesson_" + active.day + "}");

                                replaceTextInCells(sheet, "{lesson_" + active.lessonTime + ".1_lesson_" + active.day + "}", active.lesson + " " + active.lessonCode);
                                replaceTextInCells(sheet, "{lesson_" + active.lessonTime + ".2_lesson_" + active.day + "}", active.lesson + " " + active.lessonCode);

                                replaceTextInCells(sheet, "{lesson_" + active.lessonTime + ".1_place_" + active.day + "}", active.classroom + " " + active.classroomNumber);
                                replaceTextInCells(sheet, "{lesson_" + active.lessonTime + ".2_place_" + active.day + "}", active.classroom + " " + active.classroomNumber);

                                replaceTextInCells(sheet, "{lesson_" + active.lessonTime + ".1_teacher_" + active.day + "}", active.teacher + " ");
                                replaceTextInCells(sheet, "{lesson_" + active.lessonTime + ".2_teacher_" + active.day + "}", active.teacher + " ");
                            }
                            active = active.last;
                        }


                        replaceTextInCells(sheet, "{tablo_name}", Slot[0]);
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

    public static String returnClock(int minutes) {
        int clock = minutes / 60;
        int minu = minutes % 60;
        return clock + ":" + minu;
    }

    private static void replaceTextInCells(Sheet sheet, String searchText, String replacementText) {
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.STRING) {
                    String cellValue = cell.getStringCellValue();
                    if (cellValue.contains(searchText)) {
                        cell.setCellValue(cellValue.replace(searchText, replacementText));
                    }
                }
            }
        }
    }

    private static void deleteCellsStartingWith(Sheet sheet, String prefix) {
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.STRING) {
                    String cellValue = cell.getStringCellValue();
                    if (cellValue.startsWith(prefix)) {
                        // Delete the cell content
                        cell.setCellValue("");
                    }
                }
            }
        }
    }
}


