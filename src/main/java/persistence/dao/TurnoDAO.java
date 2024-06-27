package persistence.dao;

import entity.Turno;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * DAO for managing database operations on service sales ({@link Turno}).
 */
@SuppressWarnings("CallToPrintStackTrace")
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
            throw new IllegalArgumentException("El monto pagado no puede ser mayor que el monto total");

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

    /**
     * Sets sorting by payment type
     * @param ascending true if ascending order should be used
     */
    public void sortByTipoPago(boolean ascending) {
        super.setSorting("tipoPago", ascending);
    }

    /**
     * Sets sorting by time and date
     * @param ascending true if ascending order should be used
     */
    public void sortByFechaHora(boolean ascending) {
        super.setSorting("fechaHora", ascending);
    }

    /**
     * Sets sorting by amount of money charged
     * @param ascending true if ascending order should be used
     */
    public void sortByMonto(boolean ascending) {
        super.setSorting("monto", ascending);
    }

    /**
     * Sets sorting by service type
     * @param ascending true if ascending order should be used
     */
    public void sortByServicio(boolean ascending) {
        super.setSorting("servicio", ascending);
    }

    /**
     * Sets sorting by the customer's name
     * @param ascending true if ascending order should be used
     */
    public void sortByCliente(boolean ascending) {
        super.setSortingWithJoin("cliente", "nombre", ascending);
    }

    /**
     * Sets sorting by the professional's name
     * @param ascending true if ascending order should be used
     */
    public void sortByProfesional(boolean ascending) {
        super.setSortingWithJoin("profesional", "nombre", ascending);
    }

    /**
     * Sets sorting by the amount of money paid so far
     * @param ascending true if ascending order should be used
     */
    public void sortByMontoPagado(boolean ascending) {
        super.setSorting("montoPagado", ascending);
    }

    /**
     * Sets sorting by notes
     * @param ascending true if ascending order should be used
     */
    public void sortByNotas(boolean ascending) {
        super.setSorting("notas", ascending);
    }

    /**
     * Retrieves all service sales from the database within the specified date range
     * @param startDate the start date and time of the range (inclusive)
     * @param endDate the end date and time of the range (inclusive)
     * @return a {@link List} of {@link Turno} entities that fall within the specified
     * date range, or {@code null} if an exception occurs
     */
    public List<Turno> getAllWithDateRange(Date startDate, Date endDate) {
        try (Session session = sessionFactory.openSession()) {
            Query<Turno> q = session.createQuery("FROM Turno t WHERE t.fechaHora BETWEEN :start AND :end", Turno.class);
            q.setParameter("start", startDate);
            q.setParameter("end", endDate);
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
