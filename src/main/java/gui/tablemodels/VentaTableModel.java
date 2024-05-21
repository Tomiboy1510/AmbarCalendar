package gui.tablemodels;

import entity.Venta;
import persistence.dao.StandaloneEntityDAO;
import persistence.dao.VentaDAO;

import java.text.SimpleDateFormat;

public class VentaTableModel extends EntityTableModel<Venta> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public VentaTableModel(StandaloneEntityDAO<Venta> dao) {
        super(dao, new String[] {"Fecha", "Monto", "Tipo de Pago", "Items"}, 200);
    }

    @Override
    public void sortByColumn(int columnIndex) {
        if (sortingColumn == columnIndex)
            sortAscending = (! sortAscending);
        VentaDAO dao = ((VentaDAO) this.dao);
        switch (columnIndex) {
            case 0 -> dao.sortByFechaHora(sortAscending);
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
            case 0 -> dateFormat.format(v.getFechaHora());
            case 1 -> "$" + v.getMonto();
            case 2 -> v.getTipoPago();
            case 3 -> {
                StringBuilder resBuilder = new StringBuilder();
                v.getItems().forEach(item -> resBuilder
                        .append(item.getProducto().getNombre())
                        .append(" x ")
                        .append(item.getCantidad())
                        .append(" ($")
                        .append(item.getMonto())
                        .append(")\n")
                );
                resBuilder.deleteCharAt(resBuilder.length() - 1);
                yield resBuilder.toString();
            }
            default -> null;
        };
    }
}
