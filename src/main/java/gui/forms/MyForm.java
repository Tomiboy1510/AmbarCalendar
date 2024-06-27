package gui.forms;

import gui.Focusable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Abstract custom {@link JFrame} for making forms.
 */
public abstract class MyForm extends JFrame implements Focusable {

    /**
     * {@link JPanel} which holds the fields of the form
     */
    protected final JPanel panel;

    /**
     * Save button that the user is supposed to press after filling all the form fields
     */
    protected final JButton saveButton;

    /**
     * Used to implement the {@link Focusable} interface
     */
    private boolean hasFocusOwnership = true;

    /**
     * The form has a {@link Focusable} "parent" to which it gives focus ownership back upon losing it
     */
    private Focusable parent = null;

    public MyForm(String title) {
        // Close if focus is lost
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

    /**
     * Supposed to be called after constructor in every class that inherits from this one
     */
    protected void afterInit() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPanel.add(saveButton);
        panel.add(buttonPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Adds a form field preceded by a {@link JLabel} with the passed {@code name} as text
     * @param name a name for the field
     * @param field a {@link JComponent} to be added to the form panel
     */
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
