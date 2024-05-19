package persistence.dao;

import entity.AbstractEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import persistence.Subscriber;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("CallToPrintStackTrace")
public abstract class HibernateDAO<T extends AbstractEntity> {

    protected final SessionFactory sessionFactory;
    private final Class<T> entityClass;
    private final List<Subscriber> subscribers;

    private final Set<String> validParams;
    private String getAllQuery;

    public HibernateDAO(SessionFactory sessionFactory, Class<T> entityClass, Set<String> validParams) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
        this.validParams = validParams;
        subscribers = new ArrayList<>();
        getAllQuery = "FROM " + entityClass.getName();
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

    public List<T> getAll(int pageNum, int pageSize) {
        try (Session session = sessionFactory.openSession()) {
            if (pageNum < 1 || pageSize < 1)
                throw new IllegalArgumentException("Número de página o tamaño de página inválido");
            Query<T> query = session.createQuery(getAllQuery, entityClass);
            query.setFirstResult((pageNum - 1) * pageSize);
            query.setMaxResults(pageSize);

            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

    private void _delete(T entity) {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            s.remove(entity);
            t.commit();
            updateSubscribers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void _save(T entity, Session s) {
        s.persist(entity);
    }

    private void _update(T entity, Session s) {
        s.merge(entity);
    }

    private void _delete(T entity, Session s) {
        s.remove(entity);
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

    public void save(T entity, Session s) throws IllegalArgumentException {
        if (entity == null)
            throw new IllegalArgumentException("Intentando persistir una entidad 'null'");
        validate(entity);
        if (entityExists(entity))
            throw new IllegalArgumentException("La entidad provista ya existe");
        _save(entity, s);
    }

    public void update(T entity, Session s) throws IllegalArgumentException {
        validate(entity);
        if (! entityExists(entity))
            throw new IllegalArgumentException("Intentando modificar una entidad que no existe");
        _update(entity, s);
    }

    public void delete(T entity, Session s) throws IllegalArgumentException {
        if (! entityExists(entity))
            throw new IllegalArgumentException("Intentando eliminar una entidad que no existe");
        _delete(entity, s);
    }

    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    public void updateSubscribers() {
        subscribers.forEach(Subscriber::refresh);
    }

    protected boolean entityExists(T entity) {
        return get(entity.getId()) != null;
    }

    protected abstract void validate(T entity) throws IllegalArgumentException;

    protected void setSorting(String field, boolean ascending) {
        if (! validParams.contains(field)) {
            throw new RuntimeException("Parámetro de ordenamiento '" + field + "' inválido");
        }

        getAllQuery =
                "FROM " + entityClass.getName() +
                " ORDER BY " + field +
                (ascending ? " ASC" : " DESC");
    }

    protected void setSortingWithJoin(String joinField, String sortField, boolean ascending) {
        if (! validParams.contains(sortField)) {
            throw new RuntimeException("Parámetro de ordenamiento inválido");
        }
        if (! validParams.contains(joinField)) {
            throw new RuntimeException("Propiedad de Join '" + joinField + "' inválida");
        }

        getAllQuery =
                "SELECT a " +
                "FROM " + entityClass.getName() + " a " +
                "JOIN a." + joinField + " b " +
                "ORDER BY b." + sortField +
                (ascending ? " ASC" : " DESC");
    }
}
