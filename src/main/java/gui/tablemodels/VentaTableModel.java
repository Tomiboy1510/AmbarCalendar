package gui.tablemodels;

import entity.Venta;
import persistence.dao.HibernateDAO;

import java.text.SimpleDateFormat;

public class VentaTableModel extends EntityTableModel<Venta> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public VentaTableModel(HibernateDAO<Venta> dao) {
        super(dao, new String[] {"Monto", "Fecha", "Tipo de Pago", "Items"}, 200);
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
