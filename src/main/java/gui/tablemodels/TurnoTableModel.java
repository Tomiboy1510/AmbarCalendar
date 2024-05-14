package gui.tablemodels;

import entity.Turno;
import persistence.dao.HibernateDAO;

public class TurnoTableModel extends EntityTableModel<Turno> {

    public TurnoTableModel(HibernateDAO<Turno> dao) {
        super(dao);
        columnNames = new String[] {
                "Monto total",
                "Monto pagado",
                "Fecha y Hora",
                "Tipo de Pago",
                "Servicio",
                "Profesional",
                "Cliente",
                "Notas adicionales"
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Turno t = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> t.getMonto();
            case 1 -> t.getMontoPagado();
            case 2 -> t.getFechaHora();
            case 3 -> t.getTipoPago();
            case 4 -> t.getServicio();
            case 5 -> t.getProfesional();
            case 6 -> t.getCliente();
            case 7 -> t.getNotas();
            default -> null;
        };
    }
}
