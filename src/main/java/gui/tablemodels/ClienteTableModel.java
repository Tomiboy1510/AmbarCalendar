package gui.tablemodels;

import entity.Cliente;
import persistence.dao.ClienteDAO;

import javax.swing.*;

/**
 * Table model to manage and display customers ({@link Cliente}) in a {@link JTable}.
 */
public class ClienteTableModel extends StandaloneEntityTableModel<Cliente> {

    public ClienteTableModel(ClienteDAO dao) {
        super(dao, new String[] {"DNI", "Nombre", "Teléfono"}, 40);
    }

    @Override
    public void sortByColumn(int columnIndex) {
        if (sortingColumn == columnIndex)
            sortAscending = (! sortAscending);
        ClienteDAO dao = ((ClienteDAO) this.dao);
        switch (columnIndex) {
            case 0 -> dao.sortByDni(sortAscending);
            case 1 -> dao.sortByNombre(sortAscending);
            case 2 -> dao.sortByTelefono(sortAscending);
            default -> {return;}
        }
        sortingColumn = columnIndex;
        refresh();
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
