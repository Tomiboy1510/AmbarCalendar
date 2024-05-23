package persistence.dao;

import entity.ItemVenta;
import org.hibernate.SessionFactory;

public class ItemVentaDAO extends EntityDAO<ItemVenta> {

    private final ProductoDAO productoDAO;

    public ItemVentaDAO(SessionFactory sessionFactory, ProductoDAO productoDAO) {
        super(sessionFactory, ItemVenta.class);
        this.productoDAO = productoDAO;
    }

    @Override
    protected void validate(ItemVenta item) throws IllegalArgumentException {
        if (item.getCantidad() <= 0)
            throw new IllegalArgumentException("La cantidad de un item debe ser positiva");

        if (item.getMonto() < 0)
            throw new IllegalArgumentException("El monto de un item no puede ser negativo");

        if (! productoDAO.entityExists(item.getProducto()))
            throw new IllegalArgumentException("El producto de un item no existe");
    }
}
