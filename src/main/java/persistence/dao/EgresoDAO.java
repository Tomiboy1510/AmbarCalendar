package persistence.dao;

import entity.Egreso;
import org.hibernate.SessionFactory;

import java.util.Date;

public class EgresoDAO extends HibernateDAO<Egreso> {

    public EgresoDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Egreso.class);
    }

    public Egreso get(int id) {
        return super.get(id);
    }

    @Override
    protected boolean entityExists(Egreso e) {
        return get(e.getId()) != null;
    }

    @Override
    protected void validate(Egreso e) throws IllegalArgumentException {
        if (e.getMotivo() == null || e.getMotivo().isEmpty())
            throw new IllegalArgumentException("Motivo obligatorio");

        if (e.getMonto() <= 0)
            throw new IllegalArgumentException("El monto debe ser positivo");

        if (e.getFecha() == null)
            throw new IllegalArgumentException("Fecha obligatoria");

        if (e.getFecha().after(new Date()))
            throw new IllegalArgumentException("La fecha no debe ser futura");
    }
}
