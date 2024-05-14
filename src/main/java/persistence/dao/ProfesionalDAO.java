package persistence.dao;

import entity.Profesional;
import org.hibernate.SessionFactory;

public class ProfesionalDAO extends HibernateDAO<Profesional> {

    public ProfesionalDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Profesional.class);
    }

    @Override
    protected void validate(Profesional entity) throws IllegalArgumentException {

    }
}
