package gui.tablepanels;

import gui.forms.EntityForm;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductoTablePanel extends EntityTablePanel {

    public ProductoTablePanel() {
        super("Productos", new DefaultTableModel());
    }

    @Override
    protected ActionListener getAddButtonHandler() {
        return (ActionEvent e) -> {
            EntityForm form = new EntityForm("Añadir Producto") {};
            form.setVisible(true);
        };
    }

    @Override
    protected ActionListener getModifyButtonHandler() {
        return null;
    }

    @Override
    protected ActionListener getRemoveButtonHandler() {
        return null;
    }
}
