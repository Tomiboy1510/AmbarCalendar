package persistence.dao;

import entity.Venta;
import org.hibernate.SessionFactory;

public class VentaDAO extends HibernateDAO<Venta> {

    public VentaDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Venta.class);
    }

    @Override
    protected void validate(Venta entity) throws IllegalArgumentException {

    }
}
