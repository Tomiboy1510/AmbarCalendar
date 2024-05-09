package gui.forms;

import gui.MyJFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class EntityForm extends MyJFrame {

    protected JPanel panel;
    protected JButton saveButton;

    public EntityForm(String title) {
        setTitle(title);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                dispose();
            }
        });

        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10,10,0,10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        saveButton = new JButton("Guardar");
        saveButton.setFocusable(false);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(5,5,5,5));
        buttonPanel.add(saveButton);
        panel.add(buttonPanel);
    }
}
