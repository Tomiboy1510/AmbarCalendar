package gui.forms;

import gui.MyJFrame;
import persistence.dao.HibernateDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class EntityForm extends MyJFrame {

    protected JPanel panel;
    protected JButton saveButton;
    protected HibernateDAO dao;
    protected boolean isNew = true;

    public EntityForm(String title, HibernateDAO dao) {
        this.dao = dao;

        setTitle(title);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 0, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        saveButton = new JButton("Guardar");
        saveButton.setFocusable(false);

        saveButton.addActionListener(_ -> {
            Object entity = buildEntity();
            try {
                if (isNew) {
                    dao.save(entity);
                } else {
                    dao.update(entity);
                }
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

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

    protected abstract Object buildEntity();
}
