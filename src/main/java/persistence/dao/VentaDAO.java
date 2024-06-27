package persistence.dao;

import entity.ItemVenta;
import entity.Producto;
import entity.Venta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * DAO for managing database operations on product sales ({@link Venta}).
 */
@SuppressWarnings("CallToPrintStackTrace")
public class VentaDAO extends StandaloneEntityDAO<Venta> {

    /**
     * DAO for managing operations on sale items ({@link ItemVenta})
     */
    private final ItemVentaDAO itemVentaDAO;

    /**
     * DAO for managing operations on products ({@link Producto}).
     * (For example, updating stock values)
     */
    private final ProductoDAO productoDAO;

    public VentaDAO(SessionFactory sessionFactory, ItemVentaDAO itemVentaDAO, ProductoDAO productoDAO) {
        super(sessionFactory, Venta.class,
                new HashSet<>(List.of("tipoPago", "fechaHora")));
        this.itemVentaDAO = itemVentaDAO;
        this.productoDAO = productoDAO;
    }

    /**
     * Validates and (if valid) persists a sale ({@link Venta}) entity to the database
     * @param v the entity to be persisted
     * @throws IllegalArgumentException if the sale entity is null, already exists or is not valid
     */
    @Override
    public void save(Venta v) throws IllegalArgumentException {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();

            // Validate sale
            if (v == null)
                throw new IllegalArgumentException("Intentando persistir una entidad 'null'");
            if (entityExists(v))
                throw new IllegalArgumentException("La entidad provista ya existe");
            validate(v);

            /* HashMap to keep track of products whose stock value should change.
            (Many items may reference the same product but each has their own copy of it
            so this is done to avoid duplicates. Only one of the products should have its
            stock value updated) */
            HashMap<Integer, Producto> productoMap = new HashMap<>();

            // For each item in the sale
            v.getItems().forEach(item -> {
                // Persist item
                itemVentaDAO.save(item, s);

                // Change stock values
                int id = item.getProducto().getId();
                Producto p;
                if (productoMap.containsKey(id))
                    p = productoMap.get(id);
                else {
                    p = item.getProducto();
                    productoMap.put(id, p);
                }
                p.setStock(p.getStock() - item.getCantidad());
            });

            // Update products with their new stock values
            productoMap.values().forEach(p -> productoDAO.update(p, s));

            // Persist sale
            s.persist(v);

            t.commit();
            updateSubscribers();
            productoDAO.updateSubscribers();
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a sale ({@link Venta}) entity from the database
     * @param v the entity to be deleted
     * @throws IllegalArgumentException if the entity is null or doesn't exist
     */
    @Override
    public void delete(Venta v) throws IllegalArgumentException {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();

            // Validate sale
            if (v == null)
                throw new IllegalArgumentException("Intentando eliminar una entidad 'null'");
            if (! entityExists(v))
                throw new IllegalArgumentException("La entidad provista no existe");
            validate(v);

            /* HashMap to keep track of products whose stock value should change.
            (Many items may reference the same product but each has their own copy of it
            so this is done to avoid duplicates. Only one of the products should have its
            stock value updated) */
            HashMap<Integer, Producto> productoMap = new HashMap<>();

            // For each item in the sale
            v.getItems().forEach(item -> {
                // Persist item
                itemVentaDAO.delete(item, s);

                // Change stock values
                int id = item.getProducto().getId();
                Producto p;
                if (productoMap.containsKey(id))
                    p = productoMap.get(id);
                else {
                    p = item.getProducto();
                    productoMap.put(id, p);
                }
                p.setStock(p.getStock() + item.getCantidad());
            });

            // Update products with their new stock values
            productoMap.values().forEach(p -> productoDAO.update(p, s));

            // Delete sale
            s.remove(v);

            t.commit();
            updateSubscribers();
            productoDAO.updateSubscribers();
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void validate(Venta v) throws IllegalArgumentException {
        if (v.getFechaHora() == null)
            throw new IllegalArgumentException("Fecha obligatoria");

        if (v.getItems() == null || v.getItems().isEmpty())
            throw new IllegalArgumentException("Se requiere al menos un item");

        if (v.getMonto() <= 0)
            throw new IllegalArgumentException("El monto debe ser positivo");

        int montoTotal = v.getItems().stream().mapToInt(ItemVenta::getMonto).sum();
        if (montoTotal != v.getMonto())
            throw new IllegalArgumentException("El monto total no coincide con la suma del monto de cada item");
    }

    /**
     * Sets sorting by payment type
     * @param ascending true if ascending order should be used
     */
    public void sortByTipoPago(boolean ascending) {
        super.setSorting("tipoPago", ascending);
    }

    /**
     * Sets sorting by date
     * @param ascending true if ascending order should be used
     */
    public void sortByFechaHora(boolean ascending) {
        super.setSorting("fechaHora", ascending);
    }

    /**
     * Retrieves all product sales from the database within the specified date range
     * @param startDate the start date and time of the range (inclusive)
     * @param endDate the end date and time of the range (inclusive)
     * @return a {@link List} of {@link Venta} entities that fall within the specified
     * date range, or {@code null} if an exception occurs
     */
    public List<Venta> getAllWithDateRange(Date startDate, Date endDate) {
        try (Session session = sessionFactory.openSession()) {
            Query<Venta> q = session.createQuery("FROM Venta v WHERE v.fechaHora BETWEEN :start AND :end", Venta.class);
            q.setParameter("start", startDate);
            q.setParameter("end", endDate);
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
