package persistence.dao;

import entity.AbstractEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@SuppressWarnings("CallToPrintStackTrace")
public abstract class EntityDAO<T extends AbstractEntity> {

    protected final SessionFactory sessionFactory;
    protected final Class<T> entityClass;

    public EntityDAO(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    public long getCount() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(entityClass)));
            Long count = session.createQuery(criteriaQuery).uniqueResult();
            return count != null ? count : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public T get(int id) {
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

    protected void _save(T entity) {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            s.persist(entity);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void _update(T entity) {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            s.merge(entity);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void _delete(T entity) {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            s.remove(entity);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void _save(T entity, Session s) {
        s.persist(entity);
    }

    protected void save(T entity, Session s) throws IllegalArgumentException {
        if (entity == null)
            throw new IllegalArgumentException("Intentando persistir una entidad 'null'");
        validate(entity);
        if (entityExists(entity))
            throw new IllegalArgumentException("La entidad provista ya existe");
        _save(entity, s);
    }

    public void save(T entity) throws IllegalArgumentException {
        if (entity == null)
            throw new IllegalArgumentException("Intentando persistir una entidad 'null'");
        validate(entity);
        if (entityExists(entity))
            throw new IllegalArgumentException("La entidad provista ya existe");
        _save(entity);
    }

    public void update(T entity) throws IllegalArgumentException {
        validate(entity);
        if (! entityExists(entity))
            throw new IllegalArgumentException("Intentando modificar una entidad que no existe");
        _update(entity);
    }

    public void delete(T entity) throws IllegalArgumentException {
        if (! entityExists(entity))
            throw new IllegalArgumentException("Intentando eliminar una entidad que no existe");
        _delete(entity);
    }

    public boolean entityExists(T entity) {
        return get(entity.getId()) != null;
    }

    protected abstract void validate(T entity) throws IllegalArgumentException;
}
