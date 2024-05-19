package persistence.dao;

import entity.Profesional;
import org.hibernate.SessionFactory;

import java.util.HashSet;
import java.util.List;

public class ProfesionalDAO extends HibernateDAO<Profesional> {

    public ProfesionalDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Profesional.class,
                new HashSet<>(List.of("nombre", "porcentajeCobro", "salarioBasico")));
    }

    @Override
    protected void validate(Profesional p) throws IllegalArgumentException {
        if (p.getNombre() == null || p.getNombre().isEmpty())
            throw new IllegalArgumentException("Nombre obligatorio");

        if (p.getPorcentajeCobro() < 0 || p.getPorcentajeCobro() >= 100)
            throw new IllegalArgumentException("El porcentaje de cobro debe estar entre 0% y 100%");

        if (p.getSalarioBasico() < 0)
            throw new IllegalArgumentException("El salario básico no puede ser negativo");
    }

    public void sortByNombre(boolean ascending) {
        super.setSorting("nombre", ascending);
    }

    public void sortByPorcentajeCobro(boolean ascending) {
        super.setSorting("porcentajeCobro", ascending);
    }

    public void sortBySalarioBasico(boolean ascending) {
        super.setSorting("salarioBasico", ascending);
    }
}
