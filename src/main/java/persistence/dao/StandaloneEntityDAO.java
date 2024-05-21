package persistence.dao;

import entity.AbstractEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import persistence.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("CallToPrintStackTrace")
public abstract class StandaloneEntityDAO<T extends AbstractEntity> extends EntityDAO<T> {

    private final List<Subscriber> subscribers;
    private final Set<String> validParams;
    private String getAllQuery;

    public StandaloneEntityDAO(SessionFactory sessionFactory, Class<T> entityClass, Set<String> validParams) {
        super(sessionFactory, entityClass);
        this.validParams = validParams;
        subscribers = new ArrayList<>();
        getAllQuery = "FROM " + entityClass.getName();
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

    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    public void updateSubscribers() {
        subscribers.forEach(Subscriber::refresh);
    }

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

    @Override
    public void save(T entity) throws IllegalArgumentException {
        super.save(entity);
        updateSubscribers();
    }

    @Override
    public void update(T entity) throws IllegalArgumentException {
        super.update(entity);
        updateSubscribers();
    }

    @Override
    public void delete(T entity) throws IllegalArgumentException {
        super.delete(entity);
        updateSubscribers();
    }
}
