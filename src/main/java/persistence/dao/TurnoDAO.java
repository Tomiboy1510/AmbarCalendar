package persistence.dao;

import entity.Turno;
import org.hibernate.SessionFactory;

public class TurnoDAO extends HibernateDAO<Turno> {

    private final ClienteDAO clienteDAO;
    private final ProfesionalDAO profesionalDAO;

    public TurnoDAO(SessionFactory sessionFactory, ClienteDAO clienteDAO, ProfesionalDAO profesionalDAO) {
        super(sessionFactory, Turno.class);
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
}
