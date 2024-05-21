package persistence.dao;

import entity.Egreso;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class EgresoDAO extends StandaloneEntityDAO<Egreso> {

    public EgresoDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Egreso.class,
                new HashSet<>(List.of("motivo", "monto", "fecha")));
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

    public void sortByMotivo(boolean ascending) {
        super.setSorting("motivo", ascending);
    }

    public void sortByMonto(boolean ascending) {
        super.setSorting("monto", ascending);
    }

    public void sortByFecha(boolean ascending) {
        super.setSorting("fecha", ascending);
    }
}
