package persistence.dao;

import entity.ItemVenta;
import entity.Venta;
import org.hibernate.SessionFactory;

public class VentaDAO extends HibernateDAO<Venta> {

    private final ProductoDAO productoDAO;

    public VentaDAO(SessionFactory sessionFactory, ProductoDAO productoDAO) {
        super(sessionFactory, Venta.class);
        this.productoDAO = productoDAO;
    }

    public Venta get(int id) {
        return super.get(id);
    }

    @Override
    protected boolean entityExists(Venta v) {
        return get(v.getId()) != null;
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

            if (productoDAO.get(item.getProducto().getNombre()) == null)
                throw new IllegalArgumentException("El producto de un item no existe");
        }

        int montoTotal = v.getItems().stream().mapToInt(ItemVenta::getMonto).sum();
        if (montoTotal != v.getMonto())
            throw new IllegalArgumentException("El monto total no coincide con la suma del monto de cada item");
    }
}
