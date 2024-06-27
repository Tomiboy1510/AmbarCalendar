package persistence.dao;

import entity.Egreso;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * DAO for managing database operations on expenses ({@link Egreso}).
 */
@SuppressWarnings("CallToPrintStackTrace")
public class EgresoDAO extends StandaloneEntityDAO<Egreso> {

    public EgresoDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Egreso.class,
                new HashSet<>(List.of("motivo", "monto", "fecha")));
    }

    @Override
    protected void validate(Egreso e) throws IllegalArgumentException {
        if (e.getMotivo() == null || e.getMotivo().isEmpty())
            throw new IllegalArgumentException("Motivo obligatorio");

        if (e.getMonto() <= 0)
            throw new IllegalArgumentException("El monto debe ser positivo");

        if (e.getFecha() == null)
            throw new IllegalArgumentException("Fecha obligatoria");

        if (e.getFecha().after(new Date()))
            throw new IllegalArgumentException("La fecha no debe ser futura");
    }

    /**
     * Sets sorting by the reason of the expense
     * @param ascending true if ascending order should be used
     */
    public void sortByMotivo(boolean ascending) {
        super.setSorting("motivo", ascending);
    }

    /**
     * Sets sorting by the amount of money expended
     * @param ascending true if ascending order should be used
     */
    public void sortByMonto(boolean ascending) {
        super.setSorting("monto", ascending);
    }

    /**
     * Sets sorting by the date of the expense
     * @param ascending true if ascending order should be used
     */
    public void sortByFecha(boolean ascending) {
        super.setSorting("fecha", ascending);
    }

    /**
     * Retrieves all expenses from the database within the specified date range
     * @param startDate the start date and time of the range (inclusive)
     * @param endDate the end date and time of the range (inclusive)
     * @return a {@link List} of {@link Egreso} entities that fall within the specified
     * date range, or {@code null} if an exception occurs
     */
    public List<Egreso> getAllWithDateRange(Date startDate, Date endDate) {
        try (Session session = sessionFactory.openSession()) {
            Query<Egreso> q = session.createQuery("FROM Egreso e WHERE e.fecha BETWEEN :start AND :end", Egreso.class);
            q.setParameter("start", startDate);
            q.setParameter("end", endDate);
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
