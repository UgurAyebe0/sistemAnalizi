package ugurayebe.fun.controller.frame.button;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ugurayebe.fun.controller.frame.config.getColumnList;
import ugurayebe.fun.controller.frame.config.tableComboBox;
import ugurayebe.fun.database.returnListe;
import ugurayebe.fun.listener.showMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static ugurayebe.fun.controller.frame.reloadTablo.reload;



/**
 * ExcelOutputButton sınıfı, Excel çıktısı alma işlemlerini gerçekleştiren bir JButton oluşturur.
 */
public class excelcikti {

    /**
     * Excel çıktısı alma işlemlerini gerçekleştiren JButton'i oluşturan metod.
     * @param fieldNames Tablo alan isimlerini içeren ArrayList.
     * @param frameType Pencere türünü temsil eden bir String.
     * @param tabloSql Verilerin alındığı SQL sorgusunu içeren bir String.
     * @return Excel çıktısı alma işlemlerini gerçekleştiren JButton.
     */
    public static JButton excelcikti(ArrayList fieldNames, String frameType, String tabloSql) {
        JButton button = new JButton("Cıktı Al");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Dosya Yolu Seç");
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

                int userSelection = fileChooser.showSaveDialog(null);

                try {

                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        // Seçilen dosyanın yolunu belirle
                        Path outputPath = Path.of(fileChooser.getSelectedFile().toPath() + ".xlsx");
                        // Eğer dosya dizini yoksa oluştur
                        if (!Files.exists(outputPath.getParent())) {
                            Files.createDirectories(outputPath.getParent());
                        }
                        // Yeni bir Excel işbook'u oluştur
                        Workbook workbook = new XSSFWorkbook();
                        Sheet sheet = workbook.createSheet(frameType);

                        // Başlık satırını oluştur ve stil uygula
                        Row headerRow = sheet.createRow(0);
                        CellStyle headerCellStyle = workbook.createCellStyle();
                        Font headerFont = workbook.createFont();
                        headerFont.setBold(true); // Kalın yazı için
                        headerCellStyle.setFont(headerFont);

                        // Kolon isimlerini başlık satırına ekle
                        ArrayList<String> columnList = getColumnList.main(fieldNames);
                        for (int i = 0; i < columnList.size(); i++) {
                            Cell Cell = headerRow.createCell(i);
                            Cell.setCellValue(columnList.get(i));
                            Cell.setCellStyle(headerCellStyle);
                        }

                        // Tablodan verileri çek
                        ArrayList loadArryList = returnListe.main(tabloSql);
                        int rowCount = 1;

                        // Verileri Excel sayfasına ekle
                        for (int i = 0; i < loadArryList.size(); i = i + columnList.size()) {
                            Row dataRow = sheet.createRow(rowCount++);
                            for (int j = 0; j < columnList.size(); j++) {
                                // Eğer bir ComboBox alanıysa, özel işlemleri uygula
                                if (columnList.get(j).startsWith("jComboBox")) {
                                    dataRow.createCell(j).setCellValue(tableComboBox.main(columnList.get(j), loadArryList.get(i + j)));
                                    continue;
                                }
                                dataRow.createCell(j).setCellValue(String.valueOf(loadArryList.get(i + j)));
                            }
                        }

                        // Dosyayı diske yaz
                        try (FileOutputStream fileOut = new FileOutputStream(outputPath.toFile())) {
                            workbook.write(fileOut);
                            showMessage.main("Excel dosyası oluşturuldu.");
                            reload(fieldNames, tabloSql);
                        }
                        workbook.close();
                    } else {
                        System.out.println("Dosya seçme işlemi iptal edildi.");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        return button;
    }


}
