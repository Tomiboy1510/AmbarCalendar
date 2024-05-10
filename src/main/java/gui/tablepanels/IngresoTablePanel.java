package gui.tablepanels;

import entity.VentaProducto;
import gui.forms.EntityForm;
import gui.forms.VentaProductoForm;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngresoTablePanel extends EntityTablePanel {

    public IngresoTablePanel() {
        super("Ingresos", new DefaultTableModel());
    }

    @Override
    protected ActionListener getAddButtonHandler() {
        return (ActionEvent e) -> {
            EntityForm form = new VentaProductoForm();
            form.setVisible(true);
        };
    }

    @Override
    protected ActionListener getModifyButtonHandler() {
        return (ActionEvent e) -> {
            EntityForm form = new VentaProductoForm(new VentaProducto());
            form.setVisible(true);
        };
    }

    @Override
    protected ActionListener getRemoveButtonHandler() {
        return null;
    }
}
