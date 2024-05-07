package persistencia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateDAO<T> {

    private final SessionFactory sessionFactory;
    private final Class<T> entityClass;

    public HibernateDAO(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    public T findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(entityClass, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<T> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM " + entityClass.getName(), entityClass).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(int id) {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            s.remove(s.byId(entityClass).load(id));
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(T entity) {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            s.persist(entity);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(T entity) {
        try (Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            s.merge(entity);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
