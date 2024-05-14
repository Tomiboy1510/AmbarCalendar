package persistence.dao;

import entity.Turno;
import org.hibernate.SessionFactory;

public class TurnoDAO extends HibernateDAO<Turno> {

    public TurnoDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Turno.class);
    }

    @Override
    protected void validate(Turno entity) throws IllegalArgumentException {

    }
}
