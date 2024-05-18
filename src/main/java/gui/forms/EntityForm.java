package gui.forms;

import entity.AbstractEntity;
import gui.MyJFrame;
import persistence.dao.HibernateDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class EntityForm extends MyJFrame {

    protected final JPanel panel;
    protected final JButton saveButton;
    protected final HibernateDAO dao;
    protected boolean isNew = true;
    protected int id = 0;
    private boolean disposeIfLostFocus = true;

    public EntityForm(String title, HibernateDAO dao, int id) {
        this(title, dao);
        this.id = id;
    }

    public EntityForm(String title, HibernateDAO dao) {
        this.dao = dao;

        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                if (disposeIfLostFocus)
                    dispose();
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

        saveButton.addActionListener(_ -> {
            disposeIfLostFocus = false;
            AbstractEntity entity = buildEntity();
            if (entity == null) {
                disposeIfLostFocus = true;
                return;
            }
            try {
                if (isNew) {
                    dao.save(entity);
                } else {
                    dao.update(entity);
                }
                dispose();
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            disposeIfLostFocus = true;
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
        setVisible(true);
    }

    protected void addField(String name, JComponent field) {
        JLabel label = new JLabel(name);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(10, 0, 0, 0));
        panel.add(label);
        panel.add(field);
    }

    protected abstract AbstractEntity buildEntity();
}
