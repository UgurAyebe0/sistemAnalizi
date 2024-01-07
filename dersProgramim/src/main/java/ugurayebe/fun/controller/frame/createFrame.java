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
import static ugurayebe.fun.controller.frame.button.loginButton.loginButton;
import static ugurayebe.fun.controller.frame.button.nextButton.nextButton;
import static ugurayebe.fun.controller.frame.button.pdfButton.pdfButton;
import static ugurayebe.fun.controller.frame.button.removeButton.removeButton;
import static ugurayebe.fun.controller.frame.button.saveButton.saveButton;
import static ugurayebe.fun.controller.frame.button.updateButton.updateButton;
import static ugurayebe.fun.controller.frame.config.comboBox.genereted;
import static ugurayebe.fun.controller.frame.mouseListener.addMouseListenerToTable;
import static ugurayebe.fun.controller.frame.reloadTablo.reload;
import static ugurayebe.fun.listener.veriables.*;

public class createFrame {

    public static void main(String frameName, int frameSizeX, int frameSizeY, ArrayList fieldNames, int rows, String frameType, String tabloSql) {


        frame = new JFrame(frameName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameSizeX, frameSizeY);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(rows, 4));
        JPanel buttonPanel = new JPanel(new FlowLayout());

        for (Object field : fieldNames) {
            String fieldName = (String) field;
            if ((frameType.equals("episodeCourses") || frameType.equals("teacherCourses")) && fieldName.equals("Code")) {

            } else if (fieldName.startsWith("jComboBox")) {
                String methodName = fieldName.substring(9);
                JComboBox comboBox = new JComboBox<>(genereted(methodName).toArray(new String[0]));
                ComboBoxEntry comboBoxEntry = new ComboBoxEntry(fieldName, comboBox);
                comboBoxEntries.add(comboBoxEntry);
                panel.add(new JLabel(fieldName.substring(9)));
                panel.add(comboBox);
            } else {
                JTextField textField = new JTextField();
                TextFieldEntry entry = new TextFieldEntry(fieldName, textField);
                textFieldEntries.add(entry);
                panel.add(new JLabel(fieldName));
                panel.add(textField);
            }
        }

        if (fieldNames.size() == 3 && !frameType.equals("teacherCourses") && !frameType.equals("episodeCourses")) {
            panel.add(new JLabel(" "));
        }

        if (frameType.equals("login")) {
            JButton loginButton = loginButton();
            panel.add(new JLabel(""));
            panel.add(loginButton);
            frame.add(panel);
        } else if (frameType.equals("connection")) {
//            JButton connectButton = connectButton();
            panel.add(new JLabel(""));
//            panel.add(connectButton);
            frame.add(panel);
        } else {


            JButton pdfButton = pdfButton(fieldNames, frameType, tabloSql);
            buttonPanel.add(pdfButton);


            if (!frameType.equals("episodeCourses") && !frameType.equals("teacherCourses") && !frameType.equals("Days")) {
                JButton updateButton = updateButton(fieldNames, frameType, tabloSql);
                buttonPanel.add(updateButton);
            }

            JButton saveButton = saveButton(fieldNames, frameType, tabloSql);
            buttonPanel.add(saveButton);

            JButton removeButton = removeButton(fieldNames, frameType, tabloSql);
            buttonPanel.add(removeButton);

            JButton backButton = backButton();
            buttonPanel.add(backButton);

            if (frameType.equals("Days")) {
                JButton nextButton = nextButton();
                buttonPanel.add(nextButton);
            }

            Vector<String> columnNames;
            columnNames = new Vector<>();
            columnNames.add("ID");
            for (Object field : fieldNames) {
                String fieldName = (String) field;
                if (fieldName.startsWith("button")) {
                } else if (fieldName.startsWith("jComboBox")) {
                    columnNames.add(fieldName.substring(9));
                } else {
                    columnNames.add(fieldName);
                }
            }

            tableModel = new tabloModel.CustomTableModel(columnNames, 0);
            table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(buttonPanel, BorderLayout.SOUTH);
            frame.add(panel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);
            reload(fieldNames, tabloSql);

            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            addMouseListenerToTable(fieldNames, frameType);
                        }
                    }
                }
            });


        }
        frame.setVisible(true);
    }


}