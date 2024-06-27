package gui.forms;

import entity.AbstractEntity;
import persistence.dao.EntityDAO;

import javax.swing.*;

/**
 * Form for creating or modifying a "standalone entity".
 * (a "standalone entity" is one that can be created independently.
 * For example, {@link entity.ItemVenta} would not be a standalone entity,
 * as it cannot be created independently from a {@link entity.Venta}).
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class StandaloneEntityForm extends MyForm {

    /**
     * DAO used to persist entity
     */
    protected final EntityDAO dao;

    /**
     * {@code true} if it's a form for a new entity,
     * {@code false} if it's a form for modifying an existing entity
     */
    protected boolean isNew = true;

    /**
     * Holds the ID of the entity being modified, or zero if it's a new entity
     */
    protected int id = 0;

    public StandaloneEntityForm(String title, EntityDAO dao, int id) {
        this(title, dao);
        this.id = id;
    }

    public StandaloneEntityForm(String title, EntityDAO dao) {
        super(title);
        this.dao = dao;

        saveButton.addActionListener(_ -> {
            // Disable focus ownership so that the form doesn't close when losing focus to an error dialog
            setHasFocusOwnership(false);
            AbstractEntity entity = buildEntity();
            if (entity == null) {
                setHasFocusOwnership(true);
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
            setHasFocusOwnership(true);
        });
    }

    /**
     * Abstract method that builds an entity using the data in the form fields
     * @return an {@link AbstractEntity} built with the data in the form fields, or
     * {@code null} if any of the fields holds invalid data
     */
    protected abstract AbstractEntity buildEntity();
}
