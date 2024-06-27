package persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Utility class used for creating and accessing a Hibernate {@link SessionFactory}.
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Builds {@link SessionFactory} using the {@code hibernate.cfg.xml} file
     * @return a {@link SessionFactory} created from the Hibernate configuration file
     */
    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            return null;
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}