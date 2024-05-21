package persistence.dao;

import entity.Turno;
import org.hibernate.SessionFactory;

import java.util.HashSet;
import java.util.List;

public class TurnoDAO extends StandaloneEntityDAO<Turno> {

    private final ClienteDAO clienteDAO;
    private final ProfesionalDAO profesionalDAO;

    public TurnoDAO(SessionFactory sessionFactory, ClienteDAO clienteDAO, ProfesionalDAO profesionalDAO) {
        super(sessionFactory, Turno.class,
                new HashSet<>(List.of("fechaHora", "tipoPago", "monto", "servicio",
                        "cliente", "profesional", "montoPagado", "notas", "nombre")));
        this.clienteDAO = clienteDAO;
        this.profesionalDAO = profesionalDAO;
    }

    @Override
    protected void validate(Turno t) throws IllegalArgumentException {
        if (t.getMonto() <= 0)
            throw new IllegalArgumentException("El monto debe ser positivo");

        if (t.getFechaHora() == null)
            throw new IllegalArgumentException("Fecha obligatoria");

        if (t.getMontoPagado() > t.getMonto())
            throw new IllegalArgumentException("El monto debe ser positivo");

        if (t.getMontoPagado() < 0)
            throw new IllegalArgumentException("El monto pagado no puede ser negativo");

        if (t.getCliente() == null)
            throw new IllegalArgumentException("Cliente obligatorio");

        if (t.getProfesional() == null)
            throw new IllegalArgumentException("Profesional obligatorio");

        if (! clienteDAO.entityExists(t.getCliente()))
            throw new IllegalArgumentException("El cliente no existe");

        if (! profesionalDAO.entityExists(t.getProfesional()))
            throw new IllegalArgumentException("El profesional no existe");
    }

    public void sortByTipoPago(boolean ascending) {
        super.setSorting("tipoPago", ascending);
    }

    public void sortByFechaHora(boolean ascending) {
        super.setSorting("fechaHora", ascending);
    }

    public void sortByMonto(boolean ascending) {
        super.setSorting("monto", ascending);
    }

    public void sortByServicio(boolean ascending) {
        super.setSorting("servicio", ascending);
    }

    public void sortByCliente(boolean ascending) {
        super.setSortingWithJoin("cliente", "nombre", ascending);
    }

    public void sortByProfesional(boolean ascending) {
        super.setSortingWithJoin("profesional", "nombre", ascending);
    }

    public void sortByMontoPagado(boolean ascending) {
        super.setSorting("montoPagado", ascending);
    }

    public void sortByNotas(boolean ascending) {
        super.setSorting("notas", ascending);
    }
}
