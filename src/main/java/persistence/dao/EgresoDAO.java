package persistence.dao;

import entity.Egreso;
import org.hibernate.SessionFactory;

public class EgresoDAO extends HibernateDAO<Egreso> {

    public EgresoDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Egreso.class);
    }

    public Egreso get(int id) {
        return super.get(id);
    }

    @Override
    protected void validate(Egreso entity) throws IllegalArgumentException {

    }
}
