package persistence.dao;

import persistence.Subscriber;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CallToPrintStackTrace")
public abstract class HibernateDAO<T> {

    protected final SessionFactory sessionFactory;
    private final Class<T> entityClass;
    private final List<Subscriber> subscribers;

    public HibernateDAO(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;

        subscribers = new ArrayList<>();
    }

    protected T get(Object id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(entityClass, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<T> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM " + entityClass.getName(), entityClass).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<T> getAllWithPaging(int pageNum, int pageSize) {
        try (Session session = sessionFactory.openSession()) {
            Query<T> query = session.createQuery("FROM " + entityClass.getName(), entityClass);
            query.setFirstResult((pageNum - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void _delete(T entity) {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            s.remove(entity);
            t.commit();
            updateSubscribers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void _save(T entity) {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            s.persist(entity);
            t.commit();
            updateSubscribers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void _update(T entity) {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            s.merge(entity);
            t.commit();
            updateSubscribers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void save(T entity) throws IllegalArgumentException {
        if (entity == null)
            throw new IllegalArgumentException("Intentando persistir una entidad 'null'");
        validate(entity);
        if (entityExists(entity))
            throw new IllegalArgumentException("La entidad provista ya existe");
        _save(entity);
    }

    public final void update(T entity) throws IllegalArgumentException {
        validate(entity);
        if (! entityExists(entity))
            throw new IllegalArgumentException("Intentando modificar una entidad que no existe");
        _update(entity);
    }

    public final void delete(T entity) throws IllegalArgumentException {
        if (! entityExists(entity))
            throw new IllegalArgumentException("Intentando eliminar una entidad que no existe");
        _delete(entity);
    }


    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    private void updateSubscribers() {
        subscribers.forEach(Subscriber::refresh);
    }

    protected abstract boolean entityExists(T entity);

    protected abstract void validate(T entity) throws IllegalArgumentException;
}
