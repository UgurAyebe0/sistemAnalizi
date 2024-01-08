package ugurayebe.fun.view;

import ugurayebe.fun.controller.frame.createFrame;
import ugurayebe.fun.controller.program.program;
import ugurayebe.fun.view.Days.lessonSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static ugurayebe.fun.listener.alert.getEpisode.getEpisode;
import static ugurayebe.fun.listener.alert.getTeacher.getTeachers;
import static ugurayebe.fun.listener.veriables.*;


public class opMenu {
    private static JPanel opPanel;

    public static void opPermission() {

        // Field , Combobox oluşturucularımızı temizliyoruz. Tekrar etmemesi adına
        textFieldEntries.clear();
        comboBoxEntries.clear();

        // Frame default bilgileri
        frame = new JFrame("Yönetici | Menü");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 450);
        frame.setLocationRelativeTo(null);

        opPanel = new JPanel(new GridLayout(14, 1));

        // Burada bütün menülerimizi CreateFrame methodundan çağırıyoruz.

        JButton titleButton = new JButton("Ünvanlar");
        titleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ArrayList fieldName = new ArrayList();
                fieldName.add("Name");
                createFrame.main("Ünvan İşlemleri", 500, 600, fieldName,
                        1, "Title", "Select * from Title");

            }
        });
        opPanel.add(titleButton);


        JButton teacherButton = new JButton("Öğretmenler");
        teacherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ArrayList fieldName = new ArrayList();
                fieldName.add("jComboBoxTitle");
                fieldName.add("jComboBoxOp");
                fieldName.add("TC");
                fieldName.add("PhoneNumber");
                fieldName.add("Name");
                fieldName.add("Surname");
                fieldName.add("Username");
                fieldName.add("Password");
                createFrame.main("Ünvan İşlemleri", 800, 600, fieldName,
                        4, "Teacher", "Select * from Teacher ORDER BY username");

            }
        });
        opPanel.add(teacherButton);


        JButton availablesButton = new JButton("Müsaitlik");
        availablesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ArrayList fieldName = new ArrayList();
                fieldName.add("jComboBoxTeacher");
                fieldName.add("jComboBoxDay");
                fieldName.add("Reason");
                createFrame.main("Öğretmen Müsait Olmadiği Günler", 800, 600, fieldName,
                        2, "availabilityTeacher", "Select * from availabilityTeacher ORDER BY teacher");

            }
        });
        opPanel.add(availablesButton);


        JButton episodeButton = new JButton("Bölümler");
        episodeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ArrayList fieldName = new ArrayList();
                fieldName.add("Name");
                createFrame.main("Bölüm işlemleri", 500, 600, fieldName,
                        1, "Episode", "Select * from Episode ORDER BY Name");

            }
        });
        opPanel.add(episodeButton);


        JButton classroomButton = new JButton("Derslikler");
        classroomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ArrayList fieldName = new ArrayList();
                fieldName.add("Type");
                fieldName.add("Capacity");
                fieldName.add("Custom");
                createFrame.main("Derslik işlemleri", 800, 600, fieldName,
                        2, "Classroom", "Select * from Classroom ORDER BY Type");

            }
        });
        opPanel.add(classroomButton);

        JButton lessonButton = new JButton("Ders Ekleme");
        lessonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ArrayList fieldName = new ArrayList();
                fieldName.add("jComboBoxClassroom");
                fieldName.add("Code");
                fieldName.add("Name");
                fieldName.add("Time");
                fieldName.add("Student");
                fieldName.add("jComboBoxBranch");
                createFrame.main("Derslik işlemleri", 800, 600, fieldName,
                        3, "Lesson", "Select * from Lesson ORDER BY Name");
            }
        });
        opPanel.add(lessonButton);


        JButton teacherCoursesButton = new JButton("Öğretmen Ders Atama");
        teacherCoursesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ArrayList fieldName = new ArrayList();
                fieldName.add("jComboBoxTeacher");
                fieldName.add("Code");
                fieldName.add("jComboBoxLesson");
                createFrame.main("Öğretmene ders atama", 800, 600, fieldName,
                        1, "teacherCourses", "Select id,Teacher,Code,Lesson from teacherCourses ORDER BY Teacher");

            }
        });
        opPanel.add(teacherCoursesButton);

        JButton episodeCoursesButton = new JButton("Bölüme Ders Atama");
        episodeCoursesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ArrayList fieldName = new ArrayList();
                fieldName.add("jComboBoxEpisode");
                fieldName.add("Code");
                fieldName.add("jComboBoxLesson");
                createFrame.main("Bölüme ders atama", 800, 600, fieldName,
                        1, "episodeCourses", "Select id,Episode,Code,Lesson from episodeCourses ORDER BY Episode");
            }
        });
        opPanel.add(episodeCoursesButton);

        JButton daysButton = new JButton("Gün İşlemleri");
        daysButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ArrayList fieldName = new ArrayList();
                fieldName.add("Day");
                createFrame.main("Okulun Aktif Günleri işlemleri", 500, 600, fieldName,
                        1, "Days", "Select * from Days ORDER BY Day");

            }
        });
        opPanel.add(daysButton);

        JButton timeButton = new JButton("Zaman İşlemleri");
        timeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                lessonSettings.main();
            }
        });
        opPanel.add(timeButton);


        JButton allProgramButton = new JButton("Bütün Program");
        allProgramButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

                ArrayList fieldName = new ArrayList();
                fieldName.add("jComboBoxEpisode");
                fieldName.add("jComboBoxLessons");
                fieldName.add("jComboBoxTeacher");
                fieldName.add("jComboBoxDay");
                fieldName.add("jComboBoxLessonTime");
                fieldName.add("jComboBoxClassroom");
                fieldName.add("Lesson_Code");
                fieldName.add("Classroom_Number");

                createFrame.main("Okulun Aktif Günleri işlemleri", 1000, 600, fieldName,
                        4, "academic_program",
                        "Select id,Episode,Lessons,Teacher,Day,LessonTime,Classroom,Lesson_Code,Classroom_Number" +
                                " from academic_program ORDER BY Episode");
            }
        });
        opPanel.add(allProgramButton);

        JButton teacherProgramButton = new JButton("Öğretmen Programı");
        teacherProgramButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                getTeachers();
            }
        });
        opPanel.add(teacherProgramButton);

        JButton episodeProgramButton = new JButton("Bölüm Program");
        episodeProgramButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                getEpisode();
            }
        });
        opPanel.add(episodeProgramButton);


        JButton denemeButton = new JButton("Programı Tekrar Hazırla");
        denemeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                program.main();
            }
        });
        opPanel.add(denemeButton);

        frame.add(opPanel);
        frame.setVisible(true);
    }
}
