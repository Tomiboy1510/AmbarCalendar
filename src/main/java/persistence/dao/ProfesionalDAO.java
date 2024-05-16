package persistence.dao;

import entity.Profesional;
import org.hibernate.SessionFactory;

public class ProfesionalDAO extends HibernateDAO<Profesional> {

    public ProfesionalDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Profesional.class);
    }

    public Profesional get(String nombre) {
        return super.get(nombre);
    }

    @Override
    protected void validate(Profesional entity) throws IllegalArgumentException {

    }
}
