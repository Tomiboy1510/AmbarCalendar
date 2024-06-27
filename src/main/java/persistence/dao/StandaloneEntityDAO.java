package persistence.dao;

import entity.AbstractEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import persistence.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A generic DAO for managing database operations on "standalone entities"
 * (those that can be created independently of other entities).
 * @param <T> the type of the entity, which must extend {@link AbstractEntity}
 */
@SuppressWarnings("CallToPrintStackTrace")
public abstract class StandaloneEntityDAO<T extends AbstractEntity> extends EntityDAO<T> {

    /**
     * Subscribers to be notified when any database operation occurs
     */
    private final List<Subscriber> subscribers;

    /**
     * Set of allowed parameters for the sorting clause (for avoiding HQL injection)
     */
    private final Set<String> allowedSortingParams;

    /**
     * HQL query to get all entities of type {@code <T>} in the database (can be modified to add a sorting clause)
     */
    private String getAllQuery;

    public StandaloneEntityDAO(SessionFactory sessionFactory, Class<T> entityClass, Set<String> allowedSortingParams) {
        super(sessionFactory, entityClass);
        this.allowedSortingParams = allowedSortingParams;
        subscribers = new ArrayList<>();
        getAllQuery = "FROM " + entityClass.getName();
    }

    /**
     * Retrieves a paginated subset of entities of type {@code <T>} stored in the database
     * @param pageNum the page number
     * @param pageSize the page size
     * @return a {@link List} containing a paginated subset of entities of type {@code <T>} stored in the database,
     * or {@code null} if an exception occurs
     * @throws IllegalArgumentException if {@code pageNum} or {@code pageSize} is less than {@code 1}
     */
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

    /**
     * Register a subscriber to this DAO
     * @param s the subscriber to be added to the subscriber list
     */
    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    /**
     * Notify all subscribers that a change has occured
     */
    public void updateSubscribers() {
        subscribers.forEach(Subscriber::refresh);
    }

    /**
     * Set a sorting parameter for retrieving all entities with pagination
     * @param field the field to sort by
     * @param ascending true if ascending order should be used
     */
    protected void setSorting(String field, boolean ascending) {
        if (! allowedSortingParams.contains(field))
            throw new RuntimeException("Parámetro de ordenamiento '" + field + "' inválido");

        getAllQuery = "FROM " + entityClass.getName() +
                        " ORDER BY " + field + (ascending ? " ASC" : " DESC");
    }

    /**
     * Set a sorting parameter for retrieving all entities with pagination, with a
     * sorting parameter that belongs to another entity (and thus needs a table join)
     * @param joinField the field to join the two entity tables by
     * @param sortField the field to sort by
     * @param ascending true if ascending order should be used
     */
    protected void setSortingWithJoin(String joinField, String sortField, boolean ascending) {
        if (! allowedSortingParams.contains(sortField))
            throw new RuntimeException("Parámetro de ordenamiento inválido");

        if (! allowedSortingParams.contains(joinField))
            throw new RuntimeException("Propiedad de Join '" + joinField + "' inválida");

        getAllQuery = "SELECT a " +
                        "FROM " + entityClass.getName() + " a " +
                        "JOIN a." + joinField + " b " +
                        "ORDER BY b." + sortField +
                        (ascending ? " ASC" : " DESC");
    }

    /**
     * Persists an entity and notifies subscribers
     * @param entity the entity to be persisted
     * @throws IllegalArgumentException if the entity is null, if it already exists or
     * if it is not valid
     */
    @Override
    public void save(T entity) throws IllegalArgumentException {
        super.save(entity);
        updateSubscribers();
    }

    /**
     * Updates an entity and notifies subscribers
     * @param entity the entity to be updated
     * @throws IllegalArgumentException if the entity is null, if it doesn't exist or
     * if it is not valid
     */
    @Override
    public void update(T entity) throws IllegalArgumentException {
        super.update(entity);
        updateSubscribers();
    }

    /**
     * Deletes an entity and notifies subscribers
     * @param entity the entity to be deleted
     * @throws IllegalArgumentException if the entity is null or doesn't exist
     */
    @Override
    public void delete(T entity) throws IllegalArgumentException {
        super.delete(entity);
        updateSubscribers();
    }
}
