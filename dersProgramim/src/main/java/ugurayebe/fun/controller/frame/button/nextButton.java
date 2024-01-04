package ugurayebe.fun.controller.frame.button;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ugurayebe.fun.listener.veriables.frame;
import static ugurayebe.fun.view.opMenu.opPermission;

public class nextButton {
    public static JButton nextButton() {
        JButton button = new JButton("İleri Git");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                opPermission();
            }
        });
        return button;
    }
}
