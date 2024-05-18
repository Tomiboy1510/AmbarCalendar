package gui.tablemodels;

import entity.Cliente;
import persistence.dao.ClienteDAO;

public class ClienteTableModel extends EntityTableModel<Cliente> {

    public ClienteTableModel(ClienteDAO dao) {
        super(dao, Cliente.getFieldNamesButPretty(), Cliente.getFieldNames(), 40);
    }
}
