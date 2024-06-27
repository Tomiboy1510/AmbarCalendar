package gui;

/**
 * Interface to implement a system of focus ownership where a GUI
 * frame can create another component and give focus to it, and
 * get it back when it loses it (in a linked list fashion)
 */
public interface Focusable {

    /**
     * Returns {@code true} if the form has focus ownership
     * @return {@code true} if the form has focus ownership, false otherwise
     */
    boolean hasFocusOwnership();

    /**
     * Gives focus ownership to the form
     */
    void giveFocusOwnership();

    /**
     * Remove focus ownership and give it back to the parent if present (and request focus on the parent)
     */
    void removeFocusOwnership();

    /**
     * Request focus on the form
     */
    void focus();
}
