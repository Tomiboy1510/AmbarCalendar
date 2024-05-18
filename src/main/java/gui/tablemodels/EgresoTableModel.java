package gui.tablemodels;

import entity.Egreso;
import persistence.dao.HibernateDAO;

import java.text.SimpleDateFormat;

public class EgresoTableModel extends EntityTableModel<Egreso> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public EgresoTableModel(HibernateDAO<Egreso> dao) {
        super(dao, new String[] {"Motivo", "Monto", "Fecha"}, 200);
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
