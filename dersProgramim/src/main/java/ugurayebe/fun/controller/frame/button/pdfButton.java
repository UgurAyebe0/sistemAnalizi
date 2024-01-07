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


public class pdfButton {
    public static JButton pdfButton(ArrayList fieldNames, String frameType, String tabloSql) {
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
                        Path outputPath = Path.of(fileChooser.getSelectedFile().toPath() + ".xlsx");

                        if (!Files.exists(outputPath.getParent())) {
                            Files.createDirectories(outputPath.getParent());
                        }

                        Workbook workbook = new XSSFWorkbook();
                        Sheet sheet = workbook.createSheet(frameType);


                        Row headerRow = sheet.createRow(0);

                        // Stil oluştur
                        CellStyle headerCellStyle = workbook.createCellStyle();
                        Font headerFont = workbook.createFont();
                        headerFont.setBold(true); // Kalın yazı için
                        headerCellStyle.setFont(headerFont);


                        ArrayList<String> columnList = getColumnList.main(fieldNames);


                        for (int i = 0; i < columnList.size(); i++) {
                            Cell Cell = headerRow.createCell(i);
                            Cell.setCellValue(columnList.get(i));
                            Cell.setCellStyle(headerCellStyle);
                        }


                        ArrayList loadArryList = returnListe.main(tabloSql);
                        System.out.println("loadArryList " + loadArryList);
                        int rowCount = 1;

                        for (int i = 0; i < loadArryList.size(); i = i + columnList.size()) {
                            Row dataRow = sheet.createRow(rowCount++);
                            for (int j = 0; j < columnList.size(); j++) {
                                if (columnList.get(j).startsWith("jComboBox")) {
                                    dataRow.createCell(j).setCellValue(tableComboBox.main(columnList.get(j), loadArryList.get(i + j)));
                                    continue;
                                }
                                dataRow.createCell(j).setCellValue(String.valueOf(loadArryList.get(i + j)));
                            }
                        }


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
