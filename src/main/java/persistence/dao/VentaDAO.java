package persistence.dao;

import entity.ItemVenta;
import entity.Venta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;

@SuppressWarnings("CallToPrintStackTrace")
public class VentaDAO extends StandaloneEntityDAO<Venta> {

    private final ItemVentaDAO itemVentaDAO;

    public VentaDAO(SessionFactory sessionFactory, ItemVentaDAO itemVentaDAO) {
        super(sessionFactory, Venta.class,
                new HashSet<>(List.of("tipoPago", "fechaHora")));
        this.itemVentaDAO = itemVentaDAO;
    }

    @Override
    public void save(Venta v) throws IllegalArgumentException {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();

            if (v == null)
                throw new IllegalArgumentException("Intentando persistir una entidad 'null'");
            validate(v);
            if (entityExists(v))
                throw new IllegalArgumentException("La entidad provista ya existe");

            v.getItems().forEach(i -> itemVentaDAO.save(i, s));
            _save(v, s);

            t.commit();
            updateSubscribers();
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void validate(Venta v) throws IllegalArgumentException {
        if (v.getMonto() <= 0)
            throw new IllegalArgumentException("El monto debe ser positivo");

        if (v.getFechaHora() == null)
            throw new IllegalArgumentException("Fecha obligatoria");

        if (v.getItems() == null || v.getItems().isEmpty())
            throw new IllegalArgumentException("Se requiere al menos un item");

        int montoTotal = v.getItems().stream().mapToInt(ItemVenta::getMonto).sum();
        if (montoTotal != v.getMonto())
            throw new IllegalArgumentException("El monto total no coincide con la suma del monto de cada item");
    }

    public void sortByTipoPago(boolean ascending) {
        super.setSorting("tipoPago", ascending);
    }

    public void sortByFechaHora(boolean ascending) {
        super.setSorting("fechaHora", ascending);
    }
}
