package gui.tablepanels;

import entity.Cliente;
import gui.forms.ClienteForm;
import gui.forms.EntityForm;
import gui.tablemodels.ClienteTableModel;
import persistence.dao.ClienteDAO;

public class ClienteTablePanel extends EntityTablePanel<Cliente> {

    public ClienteTablePanel(ClienteDAO dao) {
        super("Clientes", new ClienteTableModel(dao));

        addButton.addActionListener(_ -> {
            EntityForm form = new ClienteForm(dao);
            form.setVisible(true);
        });

        modifyButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            ClienteTableModel model = ((ClienteTableModel) table.getModel());
            EntityForm form = new ClienteForm(model.getEntityAtRow(selectedRow), dao);
            form.setVisible(true);
        });

        removeButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            ClienteTableModel model = ((ClienteTableModel) table.getModel());
            model.delete(selectedRow);
        });
    }
}
