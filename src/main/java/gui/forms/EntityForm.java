package gui.forms;

import gui.MyJFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class EntityForm extends MyJFrame {

    protected JPanel panel;
    protected JButton saveButton;

    public EntityForm(String title) {
        setTitle(title);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                dispose();
            }
        });

        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 0, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        saveButton = new JButton("Guardar");
        saveButton.setFocusable(false);

        add(panel);
    }

    protected void afterInit() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPanel.add(saveButton);
        panel.add(buttonPanel);
        pack();
        setLocationRelativeTo(null);
    }

    protected void addField(String name, JComponent field) {
        JLabel label = new JLabel(name);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(10, 0, 0, 0));
        panel.add(label);
        panel.add(field);
    }

    protected abstract ActionListener getSaveButtonHandler();
}
