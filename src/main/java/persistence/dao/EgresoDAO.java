package persistence.dao;

import entity.Egreso;
import org.hibernate.SessionFactory;

public class EgresoDAO extends HibernateDAO<Egreso> {

    public EgresoDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Egreso.class);
    }

    @Override
    protected void validate(Egreso entity) throws IllegalArgumentException {

    }
}
