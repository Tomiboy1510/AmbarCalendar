package gui.forms;

import entity.AbstractEntity;
import persistence.dao.EntityDAO;

import javax.swing.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class StandaloneEntityForm extends MyForm {

    protected final EntityDAO dao;
    protected boolean isNew = true;
    protected int id = 0;

    public StandaloneEntityForm(String title, EntityDAO dao, int id) {
        this(title, dao);
        this.id = id;
    }

    public StandaloneEntityForm(String title, EntityDAO dao) {
        super(title);
        this.dao = dao;

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
    }

    protected abstract AbstractEntity buildEntity();
}
