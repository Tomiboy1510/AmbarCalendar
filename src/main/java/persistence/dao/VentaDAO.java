package persistence.dao;

import entity.ItemVenta;
import entity.Producto;
import entity.Venta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@SuppressWarnings("CallToPrintStackTrace")
public class VentaDAO extends HibernateDAO<Venta> {

    private final ProductoDAO productoDAO;

    public VentaDAO(SessionFactory sessionFactory, ProductoDAO productoDAO) {
        super(sessionFactory, Venta.class);
        this.productoDAO = productoDAO;
    }

    public void save(Venta v) throws IllegalArgumentException {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();

            super.save(v, t, s);
            for (ItemVenta i : v.getItems()) {
                Producto p = i.getProducto();
                p.setStock(p.getStock() - i.getCantidad());
                productoDAO.update(p, t, s);
            }

            t.commit();
            updateSubscribers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Venta v) throws IllegalArgumentException {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();

            super.delete(v, t, s);
            for (ItemVenta i : v.getItems()) {
                Producto p = i.getProducto();
                p.setStock(p.getStock() + i.getCantidad());
                productoDAO.update(p, t, s);
            }

            t.commit();
            updateSubscribers();
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

        for (ItemVenta item : v.getItems()) {
            if (item.getCantidad() <= 0)
                throw new IllegalArgumentException("La cantidad de un item debe ser positiva");

            if (item.getMonto() <= 0)
                throw new IllegalArgumentException("El monto de un item debe ser positivo");

            if (! productoDAO.entityExists(item.getProducto()))
                throw new IllegalArgumentException("El producto de un item no existe");
        }

        int montoTotal = v.getItems().stream().mapToInt(ItemVenta::getMonto).sum();
        if (montoTotal != v.getMonto())
            throw new IllegalArgumentException("El monto total no coincide con la suma del monto de cada item");
    }
}
