package gui.tablemodels;

import entity.Turno;
import persistence.dao.StandaloneEntityDAO;
import persistence.dao.TurnoDAO;

import javax.swing.*;
import java.text.SimpleDateFormat;

/**
 * Table model to manage and display service sales ({@link Turno}) in a {@link JTable}.
 */
public class TurnoTableModel extends StandaloneEntityTableModel<Turno> {

    /**
     * Date format for displaying dates and times
     */
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy (HH:mm)");

    public TurnoTableModel(StandaloneEntityDAO<Turno> dao) {
        super(dao, new String[] {"Monto Total", "Monto Pagado", "Fecha y Hora", "Tipo de Pago",
                "Servicio", "Profesional", "Cliente", "Notas Adicionales"}, 200);
    }

    @Override
    public void sortByColumn(int columnIndex) {
        if (sortingColumn == columnIndex)
            sortAscending = (! sortAscending);
        TurnoDAO dao = ((TurnoDAO) this.dao);
        switch (columnIndex) {
            case 0 -> dao.sortByMonto(sortAscending);
            case 1 -> dao.sortByMontoPagado(sortAscending);
            case 2 -> dao.sortByFechaHora(sortAscending);
            case 3 -> dao.sortByTipoPago(sortAscending);
            case 4 -> dao.sortByServicio(sortAscending);
            case 5 -> dao.sortByProfesional(sortAscending);
            case 6 -> dao.sortByCliente(sortAscending);
            case 7 -> dao.sortByNotas(sortAscending);
            default -> {return;}
        }
        sortingColumn = columnIndex;
        refresh();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Turno t = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> "$" + t.getMonto();
            case 1 -> "$" + t.getMontoPagado();
            case 2 -> dateFormat.format(t.getFechaHora());
            case 3 -> t.getTipoPago();
            case 4 -> t.getServicio();
            case 5 -> t.getProfesional().getNombre();
            case 6 -> t.getCliente().getNombre() + "\n(Tel: " + t.getCliente().getTelefono() + ")";
            case 7 -> t.getNotas();
            default -> null;
        };
    }
}
