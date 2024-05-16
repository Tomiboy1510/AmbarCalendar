package persistence.dao;

import entity.Turno;
import org.hibernate.SessionFactory;

public class TurnoDAO extends HibernateDAO<Turno> {

    public TurnoDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Turno.class);
    }

    public Turno get(int id) {
        return super.get(id);
    }

    @Override
    protected void validate(Turno entity) throws IllegalArgumentException {

    }
}
