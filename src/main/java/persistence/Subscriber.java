package persistence;

/**
 * Interface that represents an object that "subscribes" to another and gets updates from it.
 * For example: a DAO changes data in the database and notifies GUI components
 * so that they can update the data that's being shown. GUI components would have to be
 * registered as subscribers in the DAO.
 */
public interface Subscriber {

    /**
     * Notify the subscriber that a change has occurred.
     */
    void refresh();
}
