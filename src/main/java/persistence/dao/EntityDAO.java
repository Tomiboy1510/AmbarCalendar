package persistence.dao;

import entity.AbstractEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * A generic DAO for managing database operations on Hibernate entities.
 * @param <T> the type of the entity, which must extend {@link AbstractEntity}
 */
@SuppressWarnings("CallToPrintStackTrace")
public abstract class EntityDAO<T extends AbstractEntity> {

    /**
     * The Hibernate {@link SessionFactory} used to open sessions for database interactions.
     */
    protected final SessionFactory sessionFactory;

    /**
     * The Class object representing the entity type managed by this DAO.
     */
    protected final Class<T> entityClass;

    public EntityDAO(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    /**
     * Returns the quantity of entities of type {@code <T>} stored in the database
     * @return the quantity of entities of type {@code <T>} stored in the database,
     * or {@code 0} if an exception occurs
     */
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

    /**
     * Retrieves an entity of type {@code <T>} by ID from the database
     * @param id the ID of the entity to be returned
     * @return the entity of type {@code <T>} with the corresponding ID,
     * or {@code null} if an exception occurs
     */
    public T get(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(entityClass, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves all entities of type {@code <T>} from the database
     * @return a {@link List} containing all entities of type {@code <T>} stored
     * in the database, or {@code null} if an exception occurs
     */
    public List<T> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM " + entityClass.getName(), entityClass).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Persists an entity (in an independent transaction)
     * @param entity the entity to be persisted
     */
    protected void persist(T entity) {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            s.persist(entity);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an entity (in an independent transaction)
     * @param entity the entity to be updated
     */
    protected void merge(T entity) {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            s.merge(entity);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes an entity (in an independent transaction)
     * @param entity the entity to be deleted
     */
    protected void remove(T entity) {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            s.remove(entity);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Validates and persists an entity in the database (if valid)
     * using the {@link Session} passed as argument (this allows multiple database
     * operations in a single transaction)
     * @param entity the entity to be persisted
     * @param s the session to be used
     * @throws IllegalArgumentException if the entity is null, if it already exists or
     * if it is not valid
     */
    protected void save(T entity, Session s) throws IllegalArgumentException {
        if (entity == null)
            throw new IllegalArgumentException("Intentando persistir una entidad 'null'");
        if (entityExists(entity))
            throw new IllegalArgumentException("La entidad provista ya existe");
        validate(entity);
        s.persist(entity);
    }

    /**
     * Validates and updates an entity in the database (if valid)
     * using the {@link Session} passed as argument (this allows multiple database
     * operations in a single transaction)
     * @param entity the entity to be updated
     * @param s the session to be used
     * @throws IllegalArgumentException if the entity is null, if it doesn't exist or
     * if it is not valid
     */
    protected void update(T entity, Session s) throws IllegalArgumentException {
        if (entity == null)
            throw new IllegalArgumentException("Intentando modificar una entidad 'null'");
        if (! entityExists(entity))
            throw new IllegalArgumentException("La entidad provista no existe");
        validate(entity);
        s.merge(entity);
    }

    /**
     * Deletes an entity in the database using the {@link Session} passed as
     * argument (this allows multiple database operations in a single transaction)
     * @param entity the entity to be deleted
     * @param s the session to be used
     * @throws IllegalArgumentException if the entity is null or doesn't exist
     */
    protected void delete(T entity, Session s) throws IllegalArgumentException {
        if (entity == null)
            throw new IllegalArgumentException("Intentando eliminar una entidad 'null'");
        if (! entityExists(entity))
            throw new IllegalArgumentException("La entidad provista no existe");
        s.remove(entity);
    }

    /**
     * Validates and (if valid) persists an entity in the database (in an independent transaction)
     * @param entity the entity to be persisted
     * @throws IllegalArgumentException if the entity is null, if it already exists or if
     * it is not valid
     */
    public void save(T entity) throws IllegalArgumentException {
        if (entity == null)
            throw new IllegalArgumentException("Intentando persistir una entidad 'null'");
        if (entityExists(entity))
            throw new IllegalArgumentException("La entidad provista ya existe");
        validate(entity);
        persist(entity);
    }

    /**
     * Validates and (if valid) updates an entity in the database (in an independent transaction)
     * @param entity the entity to be updated
     * @throws IllegalArgumentException if the entity is null, if it doesn't exist or if
     * it is not valid
     */
    public void update(T entity) throws IllegalArgumentException {
        if (entity == null)
            throw new IllegalArgumentException("Intentando modificar una entidad 'null'");
        if (! entityExists(entity))
            throw new IllegalArgumentException("Intentando modificar una entidad que no existe");
        validate(entity);
        merge(entity);
    }

    /**
     * Deletes an entity in the database (in an independent transaction)
     * @param entity the entity to be deleted
     * @throws IllegalArgumentException if the entity is null or doesn't exist
     */
    public void delete(T entity) throws IllegalArgumentException {
        if (entity == null)
            throw new IllegalArgumentException("Intentando eliminar una entidad 'null'");
        if (! entityExists(entity))
            throw new IllegalArgumentException("Intentando eliminar una entidad que no existe");
        remove(entity);
    }

    /**
     * Checks whether an entity exists in the database
     * @param entity the entity whose existence to check for
     * @return {@code true} if the entity exists in the database, {@code false} otherwise
     */
    public boolean entityExists(T entity) {
        return get(entity.getId()) != null;
    }

    /**
     * Abstract method to validate entities (should be implemented by each specific DAO).
     * Does nothing if the entity is valid, otherwise throws an {@link IllegalArgumentException}
     * @param entity the entity to be validated
     * @throws IllegalArgumentException if the entity is not valid
     */
    protected abstract void validate(T entity) throws IllegalArgumentException;
}
