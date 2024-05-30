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

@SuppressWarnings("CallToPrintStackTrace")
public class VentaDAO extends StandaloneEntityDAO<Venta> {

    private final ItemVentaDAO itemVentaDAO;
    private final ProductoDAO productoDAO;

    public VentaDAO(SessionFactory sessionFactory, ItemVentaDAO itemVentaDAO, ProductoDAO productoDAO) {
        super(sessionFactory, Venta.class,
                new HashSet<>(List.of("tipoPago", "fechaHora")));
        this.itemVentaDAO = itemVentaDAO;
        this.productoDAO = productoDAO;
    }

    @Override
    public void save(Venta v) throws IllegalArgumentException {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();

            if (v == null)
                throw new IllegalArgumentException("Intentando persistir una entidad 'null'");
            if (entityExists(v))
                throw new IllegalArgumentException("La entidad provista ya existe");
            validate(v);

            HashMap<Integer, Producto> productoMap = new HashMap<>();

            v.getItems().forEach(item -> {
                itemVentaDAO.save(item, s);

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

            productoMap.values().forEach(p -> productoDAO.update(p, s));

            _save(v, s);

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
    public void delete(Venta v) throws IllegalArgumentException {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();

            if (v == null)
                throw new IllegalArgumentException("Intentando eliminar una entidad 'null'");
            if (! entityExists(v))
                throw new IllegalArgumentException("La entidad provista no existe");
            validate(v);

            HashMap<Integer, Producto> productoMap = new HashMap<>();

            v.getItems().forEach(item -> {
                itemVentaDAO.delete(item, s);

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

            productoMap.values().forEach(p -> productoDAO.update(p, s));

            _delete(v, s);

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

    public void sortByTipoPago(boolean ascending) {
        super.setSorting("tipoPago", ascending);
    }

    public void sortByFechaHora(boolean ascending) {
        super.setSorting("fechaHora", ascending);
    }

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
