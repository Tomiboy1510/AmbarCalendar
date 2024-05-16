package persistence.dao;

import entity.Cliente;
import org.hibernate.SessionFactory;

public class ClienteDAO extends HibernateDAO<Cliente> {

    public ClienteDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Cliente.class);
    }

    public Cliente get(String dni) {
        return super.get(dni);
    }

    @Override
    protected void validate(Cliente entity) throws IllegalArgumentException {
        if (entity.getDni().isEmpty())
            throw new IllegalArgumentException("DNI obligatorio");

        if (entity.getNombre().isEmpty())
            throw new IllegalArgumentException("Nombre obligatorio");

        if (entity.getTelefono().isEmpty())
            throw new IllegalArgumentException("Teléfono obligatorio");
    }
}
