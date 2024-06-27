package persistence.dao;

import entity.Producto;
import org.hibernate.SessionFactory;

import java.util.HashSet;
import java.util.List;

/**
 * DAO for managing database operations on products ({@link Producto}).
 */
public class ProductoDAO extends StandaloneEntityDAO<Producto> {

    public ProductoDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Producto.class,
                new HashSet<>(List.of("nombre", "marca", "costo", "precio", "stock")));
    }

    @Override
    public void validate(Producto p) throws IllegalArgumentException {
        if (p.getNombre() == null || p.getNombre().isEmpty())
            throw new IllegalArgumentException("Nombre obligatorio");

        if (p.getMarca() == null || p.getMarca().isEmpty())
            throw new IllegalArgumentException("Marca obligatoria");

        if (p.getCosto() <= 0)
            throw new IllegalArgumentException("El costo debe ser positivo");

        if (p.getPrecio() <= 0)
            throw new IllegalArgumentException("El precio debe ser positivo");
    }

    /**
     * Sets sorting by name
     * @param ascending true if ascending order should be used
     */
    public void sortByNombre(boolean ascending) {
        super.setSorting("nombre", ascending);
    }

    /**
     * Sets sorting by brand
     * @param ascending true if ascending order should be used
     */
    public void sortByMarca(boolean ascending) {
        super.setSorting("marca", ascending);
    }

    /**
     * Sets sorting by cost
     * @param ascending true if ascending order should be used
     */
    public void sortByCosto(boolean ascending) {
        super.setSorting("costo", ascending);
    }

    /**
     * Sets sorting by selling price
     * @param ascending true if ascending order should be used
     */
    public void sortByPrecio(boolean ascending) {
        super.setSorting("precio", ascending);
    }

    /**
     * Sets sorting by amount in stock
     * @param ascending true if ascending order should be used
     */
    public void sortByStock(boolean ascending) {
        super.setSorting("stock", ascending);
    }
}
