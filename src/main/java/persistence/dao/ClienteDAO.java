package persistence.dao;

import entity.Cliente;
import org.hibernate.SessionFactory;

import java.util.HashSet;
import java.util.List;

public class ClienteDAO extends StandaloneEntityDAO<Cliente> {

    public ClienteDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Cliente.class,
                new HashSet<>(List.of("dni", "nombre", "telefono")));
    }

    @Override
    protected void validate(Cliente c) throws IllegalArgumentException {
        if (c.getDni() <= 0)
            throw new IllegalArgumentException("DNI debe ser positivo");

        if (c.getNombre() == null || c.getNombre().isEmpty())
            throw new IllegalArgumentException("Nombre obligatorio");

        if (c.getTelefono() == null || c.getTelefono().isEmpty())
            throw new IllegalArgumentException("Teléfono obligatorio");
    }

    public void sortByDni(boolean ascending) {
        super.setSorting("dni", ascending);
    }

    public void sortByNombre(boolean ascending) {
        super.setSorting("nombre", ascending);
    }

    public void sortByTelefono(boolean ascending) {
        super.setSorting("telefono", ascending);
    }
}
