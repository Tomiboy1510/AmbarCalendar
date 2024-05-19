package persistence.dao;

import entity.Producto;
import org.hibernate.SessionFactory;

import java.util.HashSet;
import java.util.List;

public class ProductoDAO extends HibernateDAO<Producto> {

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

    public void sortByNombre(boolean ascending) {
        super.setSorting("nombre", ascending);
    }

    public void sortByMarca(boolean ascending) {
        super.setSorting("marca", ascending);
    }

    public void sortByCosto(boolean ascending) {
        super.setSorting("costo", ascending);
    }

    public void sortByPrecio(boolean ascending) {
        super.setSorting("precio", ascending);
    }

    public void sortByStock(boolean ascending) {
        super.setSorting("stock", ascending);
    }
}
