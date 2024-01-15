package ugurayebe.fun.controller.frame;

import ugurayebe.fun.listener.ComboBoxEntry;
import ugurayebe.fun.listener.TextFieldEntry;
import ugurayebe.fun.listener.tabloModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import static ugurayebe.fun.controller.frame.button.backButton.backButton;
import static ugurayebe.fun.controller.frame.button.excelcikti.excelcikti;
import static ugurayebe.fun.controller.frame.button.loginButton.loginButton;
import static ugurayebe.fun.controller.frame.button.nextButton.nextButton;
import static ugurayebe.fun.controller.frame.button.removeButton.removeButton;
import static ugurayebe.fun.controller.frame.button.saveButton.saveButton;
import static ugurayebe.fun.controller.frame.button.updateButton.updateButton;
import static ugurayebe.fun.controller.frame.button.excelCKT.excelCKT;
import static ugurayebe.fun.controller.frame.config.comboBox.genereted;
import static ugurayebe.fun.controller.frame.mouseListener.addMouseListenerToTable;
import static ugurayebe.fun.controller.frame.reloadTablo.reload;
import static ugurayebe.fun.listener.veriables.*;

public class createFrame {

    public static void main(String frameName, int frameSizeX, int frameSizeY, ArrayList fieldNames, int rows, String frameType, String tabloSql) {

        // Parametrelerden alınan bilgileri buradan frame oluşturur.
        frame = new JFrame(frameName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameSizeX, frameSizeY);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(rows, 4));
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Arrylistin içine göre Combobox, Field ve Label oluşturur.
        for (Object field : fieldNames) {
            String fieldName = (String) field;

            // Burada oluşturmasını istemediğimiz FrameTypelerde durum belirtiyoruz. Mesela EpisodeCourses ve TeacherCourses Typelerinde Code
            // Varsa oluşturmamasını ve LoginUsername Typesinde de oluşturmamasını istiyoruz.
            if ((frameType.equals("episodeCourses") || frameType.equals("teacherCourses")) && fieldName.equals("Code")
                    || frameType.equals("loginUsername")) {

                // Eğer başında jComboBox varsa....
            } else if (fieldName.startsWith("jComboBox")) {
                // jComboBox siliyoruz...
                String methodName = fieldName.substring(9);
                // JComboBox oluşturuyoruz...
                JComboBox comboBox = new JComboBox<>(genereted(methodName).toArray(new String[0]));
                // ComboBoxEntry içine ekliyoruz.
                ComboBoxEntry comboBoxEntry = new ComboBoxEntry(fieldName, comboBox);
                comboBoxEntries.add(comboBoxEntry);
                // Panele ekliyoruz. Labelini
                panel.add(new JLabel(fieldName.substring(9)));
                // Daha sonra ComboBoxu
                panel.add(comboBox);
            } else {
                // Entry ekleme işlemleri
                JTextField textField = new JTextField();
                TextFieldEntry entry = new TextFieldEntry(fieldName, textField);
                textFieldEntries.add(entry);

                // Panele ekleme
                panel.add(new JLabel(fieldName));
                panel.add(textField);
            }
        }

        if (fieldNames.size() == 3 && !frameType.equals("teacherCourses") && !frameType.equals("episodeCourses")) {
            panel.add(new JLabel(" "));
        }

        // Frame Type Login ise sadece login buttonu ekle
        if (frameType.equals("login")) {
            JButton loginButton = loginButton();
            panel.add(new JLabel(""));
            panel.add(loginButton);
            frame.add(panel);

        }
        // Eğer değilse.....
        else {
            // LoginUsername = Normal öğretmen girdiğinde butonları eklememesi için...
            if (!frameType.equals("loginUsername")) {

                // Eğer Academic program öğretmen veya Bölüme göreyse..... Özel cıktı buton ekle
                if (frameType.equals("academic_program_episode") || frameType.equals("academic_program_teacher")) {
                    JButton excelCKT = excelCKT(frameType, frameName);
                    buttonPanel.add(excelCKT);
                }

                // Normal cıktı butonu ekleme
                JButton pdfButton = excelcikti(fieldNames, frameType, tabloSql);
                buttonPanel.add(pdfButton);

                // Eğer Gün,Bölüm ve Öğretmene ders atama değilse güncelleme butonu ekle
                if (!frameType.equals("episodeCourses") && !frameType.equals("teacherCourses") && !frameType.equals("Days")) {
                    JButton updateButton = updateButton(fieldNames, frameType, tabloSql);
                    buttonPanel.add(updateButton);
                }

                // Kayıt etme butonu
                JButton saveButton = saveButton(fieldNames, frameType, tabloSql);
                buttonPanel.add(saveButton);

                // Sİlme Butonu
                JButton removeButton = removeButton(fieldNames, frameType, tabloSql);
                buttonPanel.add(removeButton);

                // Geri Butonu
                JButton backButton = backButton();
                buttonPanel.add(backButton);

                // Eğer Gün ise ileri git butonu ekle
                if (frameType.equals("Days")) {
                    JButton nextButton = nextButton();
                    buttonPanel.add(nextButton);
                }
            }

            // Sütun isimlerini tutacak Vector'ü oluştur
            Vector<String> columnNames;

            columnNames = new Vector<>();
            // İlk sütunun adını "ID" olarak belirle
            columnNames.add("ID");
            // fieldNames listesindeki her bir alan adı üzerinde döngü
            for (Object field : fieldNames) {
                // Alan adını String türüne dönüştür
                String fieldName = (String) field;
                // Eğer alan adı "button" ile başlıyorsa, bir şey yapma (şu anda atlanıyor)
                if (fieldName.startsWith("button")) {
                    // Bu durumu atla, herhangi bir işlem yapma
                }
                // Eğer alan adı "jComboBox" ile başlıyorsa
                else if (fieldName.startsWith("jComboBox")) {
                    // "jComboBox" öneki kaldırılarak sütun adını Vector'e ekle
                    columnNames.add(fieldName.substring(9));
                }
                // Diğer durumlar için (normal alan adları)
                else {
                    columnNames.add(fieldName);
                    // Alan adını doğrudan sütun adı olarak Vector'e ekle
                }

            }

            // Özel bir tablo modeli oluştur, sütun isimleri ve başlangıç satır sayısıyla birlikte
            tableModel = new tabloModel.CustomTableModel(columnNames, 0);
            // Oluşturulan tablo modelini kullanarak bir tablo oluştur
            table = new JTable(tableModel);
            // Tabloyu sarmak için bir kaydırma bölmesi oluştur
            JScrollPane scrollPane = new JScrollPane(table);
            // Butonların bulunduğu bir paneli güney bölgesine ekle
            frame.add(buttonPanel, BorderLayout.SOUTH);
            // Diğer bileşenleri eklemek için iki panel oluştur
            frame.add(panel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);
            // Tabloyu güncelleyerek başlangıç verilerini yükle
            reload(fieldNames, tabloSql);
// Tablo üzerinde fare tıklamalarını dinlemek için MouseListener ekle
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Eğer fare çift tıklaması yapıldıysa
                    if (e.getClickCount() == 2) {
                        // Seçilen satırı al
                        int selectedRow = table.getSelectedRow();
                        // Eğer herhangi bir satır seçilmişse
                        if (selectedRow != -1) {
                            // Tabloya fare tıklaması olayını dinlemek için özel bir metod çağrısı
                            addMouseListenerToTable(fieldNames, frameType);
                        }
                    }
                }
            });


        }
        frame.setVisible(true);
    }


}