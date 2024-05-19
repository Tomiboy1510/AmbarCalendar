package gui.tablemodels;

import entity.Venta;
import persistence.dao.HibernateDAO;
import persistence.dao.VentaDAO;

import java.text.SimpleDateFormat;

public class VentaTableModel extends EntityTableModel<Venta> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public VentaTableModel(HibernateDAO<Venta> dao) {
        super(dao, new String[] {"Monto", "Fecha", "Tipo de Pago", "Items"}, 200);
    }

    @Override
    public void sortByColumn(int columnIndex) {
        if (sortingColumn == columnIndex)
            sortAscending = (! sortAscending);
        VentaDAO dao = ((VentaDAO) this.dao);
        switch (columnIndex) {
            case 1 -> dao.sortByFechaHora(sortAscending);
            case 2 -> dao.sortByTipoPago(sortAscending);
            default -> {return;}
        }
        sortingColumn = columnIndex;
        refresh();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venta v = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> "$" + v.getMonto();
            case 1 -> dateFormat.format(v.getFechaHora());
            case 2 -> v.getTipoPago();
            case 3 -> v.getItems();
            default -> null;
        };
    }
}
