package gui.tablemodels;

import entity.Egreso;
import persistence.dao.HibernateDAO;

public class EgresoTableModel extends EntityTableModel<Egreso> {

    public EgresoTableModel(HibernateDAO<Egreso> dao) {
        super(dao);
        columnNames = new String[] {
                "Motivo",
                "Monto",
                "Fecha"
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Egreso e = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> e.getMotivo();
            case 1 -> e.getMonto();
            case 2 -> e.getFecha();
            default -> null;
        };
    }
}
