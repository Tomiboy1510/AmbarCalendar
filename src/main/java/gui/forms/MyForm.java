package gui.forms;

import gui.Focusable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class MyForm extends JFrame implements Focusable {

    protected final JPanel panel;
    protected final JButton saveButton;

    private boolean hasFocusOwnership = true;
    private Focusable parent = null;

    public MyForm(String title) {
        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                if (hasFocusOwnership()) {
                    removeFocusOwnership();
                    dispose();
                }
            }
        });

        setTitle(title);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 0, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        saveButton = new JButton("Guardar");
        saveButton.setFocusable(false);

        add(panel);
    }

    public MyForm(String title, Focusable parent) {
        this(title);
        this.parent = parent;
    }

    protected void afterInit() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPanel.add(saveButton);
        panel.add(buttonPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    protected void addField(String name, JComponent field) {
        JLabel label = new JLabel(name);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(10, 0, 0, 0));
        panel.add(label);
        panel.add(field);
    }

    @Override
    public boolean hasFocusOwnership() {
        return hasFocusOwnership;
    }

    @Override
    public void giveFocusOwnership() {
        hasFocusOwnership = true;
    }

    @Override
    public void removeFocusOwnership() {
        hasFocusOwnership = false;
        if (parent != null) {
            parent.giveFocusOwnership();
            parent.focus();
        }
    }

    @Override
    public void focus() {
        requestFocus();
    }

    public void setHasFocusOwnership(boolean hasFocusOwnership) {
        this.hasFocusOwnership = hasFocusOwnership;
    }
}
