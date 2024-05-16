package gui.tablepanels;

import entity.Cliente;
import gui.forms.ClienteForm;
import gui.tablemodels.ClienteTableModel;
import persistence.dao.ClienteDAO;

public class ClienteTablePanel extends EntityTablePanel<Cliente> {

    public ClienteTablePanel(ClienteDAO dao) {
        super("Clientes", new ClienteTableModel(dao));

        addButton.addActionListener(_ -> {
            openForm(new ClienteForm(dao));
        });

        modifyButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            ClienteTableModel model = ((ClienteTableModel) table.getModel());
            openForm(new ClienteForm(model.getEntityAtRow(selectedRow), dao));
        });
    }
}
