package gui.tablepanels;

import entity.Egreso;
import gui.forms.EgresoForm;
import gui.forms.EntityForm;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EgresoTablePanel extends EntityTablePanel {

    public EgresoTablePanel() {
        super("Egresos", new DefaultTableModel());
    }

    @Override
    protected ActionListener getAddButtonHandler() {
        return (ActionEvent e) -> {
            EntityForm form = new EgresoForm();
            form.setVisible(true);
        };
    }

    @Override
    protected ActionListener getModifyButtonHandler() {
        return (ActionEvent e) -> {
            EntityForm form = new EgresoForm(new Egreso());
            form.setVisible(true);
        };
    }

    @Override
    protected ActionListener getRemoveButtonHandler() {
        return null;
    }
}
