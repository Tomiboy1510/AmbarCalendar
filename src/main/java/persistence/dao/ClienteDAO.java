package persistence.dao;

import entity.Cliente;
import org.hibernate.SessionFactory;

public class ClienteDAO extends HibernateDAO<Cliente> {

    public ClienteDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Cliente.class);
    }

    @Override
    protected void validate(Cliente entity) throws IllegalArgumentException {

    }
}
