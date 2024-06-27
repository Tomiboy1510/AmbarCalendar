package gui.tablepanels;

import entity.Cliente;
import gui.forms.ClienteForm;
import gui.tablemodels.ClienteTableModel;
import persistence.dao.ClienteDAO;

/**
 * GUI component used for displaying customers ({@link Cliente}) and allowing the user to
 * create new ones and modify or delete existing ones.
 */
public class ClienteTablePanel extends StandaloneEntityTablePanel<Cliente> {

    public ClienteTablePanel(ClienteDAO dao) {
        super("Clientes", new ClienteTableModel(dao));

        // Show form for new customer
        addButton.addActionListener(_ -> new ClienteForm(dao));

        // Does not allow removing (another entity may depend on this one)
        removeButton.setVisible(false);

        // Show form for modifying
        modifyButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            ClienteTableModel model = ((ClienteTableModel) table.getModel());
            new ClienteForm(model.getEntityAtRow(selectedRow), dao);
        });
    }
}
