package gui;

public interface Focusable {

    boolean hasFocusOwnership();

    void giveFocusOwnership();

    void removeFocusOwnership();

    void focus();
}
