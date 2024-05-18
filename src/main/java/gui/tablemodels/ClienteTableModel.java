package gui.tablemodels;

import entity.Cliente;
import persistence.dao.ClienteDAO;

public class ClienteTableModel extends EntityTableModel<Cliente> {

    public ClienteTableModel(ClienteDAO dao) {
        super(dao, new String[] {"DNI", "Nombre", "Teléfono"}, 40);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente c = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> c.getDni();
            case 1 -> c.getNombre();
            case 2 -> c.getTelefono();
            default -> null;
        };
    }
}
