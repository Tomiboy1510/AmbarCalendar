package gui.tablepanels;

import entity.Producto;
import gui.forms.EntityForm;
import gui.forms.ProductoForm;

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
            EntityForm form = new ProductoForm();
            form.setVisible(true);
        };
    }

    @Override
    protected ActionListener getModifyButtonHandler() {
        return (ActionEvent e) -> {
            EntityForm form = new ProductoForm(new Producto());
            form.setVisible(true);
        };
    }

    @Override
    protected ActionListener getRemoveButtonHandler() {
        return null;
    }
}
