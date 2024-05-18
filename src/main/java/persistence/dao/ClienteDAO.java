package persistence.dao;

import entity.Cliente;
import org.hibernate.SessionFactory;

public class ClienteDAO extends HibernateDAO<Cliente> {

    public ClienteDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Cliente.class);
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
}
