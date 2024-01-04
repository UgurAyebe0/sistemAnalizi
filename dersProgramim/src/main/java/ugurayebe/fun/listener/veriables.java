package ugurayebe.fun.listener;

import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.ArrayList;

public class veriables {
    public static JFrame frame;

    public static ArrayList<TextFieldEntry> textFieldEntries = new ArrayList<>();
    public static ArrayList<ComboBoxEntry> comboBoxEntries = new ArrayList<>();
    public static tabloModel.CustomTableModel tableModel;
    public static JTable table;

    public static DefaultListModel<String>  addDaysListModel = new  DefaultListModel<>();
    public static DefaultListModel<String> removeDaysListModel = new DefaultListModel<>();

    public static JList<String> unSelectDays;
    public static JList<String> selectDays;


    public static int lessonStartHours;
    public static int lessonMinutesHours;
    public static int lessonDurabity;
    public static int recessDurabity;

    public static JComboBox<String> lessonStartHoursComboBox;
    public static JComboBox<String> lessonMinutesHoursComboBox;
    public static JTextField lessonDurabityComboBox;
    public static JTextField recessDurabityComboBox;


    public static JComboBox<String> lunchHoursComboBox;
    public static JTextField lunchDurabityField;
    public static int lunchHours;
    public static int lunchDuration;


    public static int lessonFinishHours;
    public static int lessonFinishMinutes;
    public static JComboBox<String> lessonFinisComboBox;


}
