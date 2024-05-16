package persistence.dao;

import entity.Profesional;
import org.hibernate.SessionFactory;

public class ProfesionalDAO extends HibernateDAO<Profesional> {

    public ProfesionalDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Profesional.class);
    }

    @Override
    protected void validate(Profesional p) throws IllegalArgumentException {
        if (p.getNombre() == null || p.getNombre().isEmpty())
            throw new IllegalArgumentException("Nombre obligatorio");

        if (p.getPorcentajeCobro() <= 0 || p.getPorcentajeCobro() >= 100)
            throw new IllegalArgumentException("El porcentaje de cobro debe estar entre 0% y 100%");

        if (p.getSalarioBasico() <= 0)
            throw new IllegalArgumentException("El salario básico debe ser positivo");
    }
}
