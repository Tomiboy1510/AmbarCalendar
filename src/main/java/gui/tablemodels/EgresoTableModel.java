package gui.tablemodels;

import entity.Egreso;
import persistence.dao.EgresoDAO;
import persistence.dao.StandaloneEntityDAO;

import javax.swing.*;
import java.text.SimpleDateFormat;

/**
 * Table model to manage and display expenses ({@link Egreso}) in a {@link JTable}.
 */
public class EgresoTableModel extends StandaloneEntityTableModel<Egreso> {

    /**
     * Date format for displaying dates
     */
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public EgresoTableModel(StandaloneEntityDAO<Egreso> dao) {
        super(dao, new String[] {"Motivo", "Monto", "Fecha"}, 200);
    }

    @Override
    public void sortByColumn(int columnIndex) {
        if (sortingColumn == columnIndex)
            sortAscending = (! sortAscending);
        EgresoDAO dao = ((EgresoDAO) this.dao);
        switch (columnIndex) {
            case 0 -> dao.sortByMotivo(sortAscending);
            case 1 -> dao.sortByMonto(sortAscending);
            case 2 -> dao.sortByFecha(sortAscending);
            default -> {return;}
        }
        sortingColumn = columnIndex;
        refresh();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Egreso e = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> e.getMotivo();
            case 1 -> "$" + e.getMonto();
            case 2 -> dateFormat.format(e.getFecha());
            default -> null;
        };
    }
}
