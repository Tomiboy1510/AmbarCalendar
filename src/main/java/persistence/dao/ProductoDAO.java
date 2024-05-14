package persistence.dao;

import entity.Producto;
import org.hibernate.SessionFactory;

public class ProductoDAO extends HibernateDAO<Producto> {

    public ProductoDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Producto.class);
    }

    @Override
    public void validate(Producto p) throws IllegalArgumentException {

    }
}
