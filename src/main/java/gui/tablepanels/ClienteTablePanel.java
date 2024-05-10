package gui.tablepanels;

import entity.Cliente;
import gui.forms.ClienteForm;
import gui.forms.EntityForm;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteTablePanel extends EntityTablePanel {

    public ClienteTablePanel() {
        super("Clientes", new DefaultTableModel());
    }

    @Override
    protected ActionListener getAddButtonHandler() {
        return (ActionEvent e) -> {
            EntityForm form = new ClienteForm();
            form.setVisible(true);
        };
    }

    @Override
    protected ActionListener getModifyButtonHandler() {
        return (ActionEvent e) -> {
            EntityForm form = new ClienteForm(new Cliente());
            form.setVisible(true);
        };
    }

    @Override
    protected ActionListener getRemoveButtonHandler() {
        return null;
    }
}
