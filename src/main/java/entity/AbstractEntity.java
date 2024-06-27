package entity;

/**
 * Interface to be implemented by all entities to be persisted using Hibernate.
 *
 * <p>
 *     I would rather have this be a base class with an 'id' member variable,
 *     and have entities extend it. But Hibernate would generate a single
 *     auto-incrementing sequence to generate id's for all entities.
 * </p>
 */
public interface AbstractEntity {
    int getId();
    void setId(int id);
}
