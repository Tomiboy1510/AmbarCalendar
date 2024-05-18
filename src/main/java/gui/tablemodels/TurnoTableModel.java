package gui.tablemodels;

import entity.Turno;
import persistence.dao.HibernateDAO;

import java.text.SimpleDateFormat;

public class TurnoTableModel extends EntityTableModel<Turno> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy (HH:mm)");

    public TurnoTableModel(HibernateDAO<Turno> dao) {
        super(dao, new String[] {"Monto total", "Monto pagado", "Fecha y Hora", "Tipo de Pago",
                "Servicio", "Profesional", "Cliente", "Notas adicionales"}, 200);
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
            case 6 -> t.getCliente().getNombre() + " (" + "Tel: " + t.getCliente().getTelefono() + ")";
            case 7 -> t.getNotas();
            default -> null;
        };
    }
}
