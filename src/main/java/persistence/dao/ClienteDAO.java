package persistence.dao;

import entity.Cliente;
import org.hibernate.SessionFactory;

public class ClienteDAO extends HibernateDAO<Cliente> {

    public ClienteDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Cliente.class);
    }

    @Override
    protected void validate(Cliente c) throws IllegalArgumentException {
        if (c.getDni() == null || c.getDni().isEmpty())
            throw new IllegalArgumentException("DNI obligatorio");

        if (c.getNombre() == null || c.getNombre().isEmpty())
            throw new IllegalArgumentException("Nombre obligatorio");

        if (c.getTelefono() == null || c.getTelefono().isEmpty())
            throw new IllegalArgumentException("Teléfono obligatorio");
    }
}
