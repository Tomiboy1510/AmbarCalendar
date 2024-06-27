package persistence.dao;

import entity.Profesional;
import org.hibernate.SessionFactory;

import java.util.HashSet;
import java.util.List;

/**
 * DAO for managing database operations on professionals ({@link Profesional}).
 */
public class ProfesionalDAO extends StandaloneEntityDAO<Profesional> {

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

    /**
     * Sets sorting by name
     * @param ascending true if ascending order should be used
     */
    public void sortByNombre(boolean ascending) {
        super.setSorting("nombre", ascending);
    }

    /**
     * Sets sorting by commission rate
     * @param ascending true if ascending order should be used
     */
    public void sortByPorcentajeCobro(boolean ascending) {
        super.setSorting("porcentajeCobro", ascending);
    }

    /**
     * Sets sorting by base salary
     * @param ascending true if ascending order should be used
     */
    public void sortBySalarioBasico(boolean ascending) {
        super.setSorting("salarioBasico", ascending);
    }
}
