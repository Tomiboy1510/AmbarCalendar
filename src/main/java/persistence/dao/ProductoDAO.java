package persistence.dao;

import entity.Producto;
import org.hibernate.SessionFactory;

public class ProductoDAO extends HibernateDAO<Producto> {

    public ProductoDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Producto.class);
    }

    public Producto get(String nombre) {
        return super.get(nombre);
    }

    @Override
    protected boolean entityExists(Producto p) {
        return get(p.getNombre()) != null;
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
}
