package ugurayebe.fun.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.*;
import java.util.ArrayList;

public class veriables {
    public static JFrame frame;

    public static ArrayList<TextFieldEntry> textFieldEntries = new ArrayList<>();
    public static ArrayList<ComboBoxEntry> comboBoxEntries = new ArrayList<>();


    public static tabloModel.CustomTableModel tableModel;
    public static JTable table;

}
