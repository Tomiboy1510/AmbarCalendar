package gui.tablepanels;

import entity.Profesional;
import gui.forms.EntityForm;
import gui.forms.ProfesionalForm;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfesionalTablePanel extends EntityTablePanel {
    public ProfesionalTablePanel() {
        super("Profesionales", new DefaultTableModel());
    }

    @Override
    protected ActionListener getAddButtonHandler() {
        return (ActionEvent e) -> {
            EntityForm form = new ProfesionalForm();
            form.setVisible(true);
        };
    }

    @Override
    protected ActionListener getModifyButtonHandler() {
        return (ActionEvent e) -> {
            EntityForm form = new ProfesionalForm(new Profesional());
            form.setVisible(true);
        };
    }

    @Override
    protected ActionListener getRemoveButtonHandler() {
        return null;
    }
}
