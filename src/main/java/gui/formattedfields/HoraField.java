package gui.formattedfields;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * Custom JTextField to input times in the "HH:mm" format. Does not allow removing text, only editing.
 * Text will only be valid if it matches the regex {@code "\\d{1,2}:\\d{1,2}"}.
 */
public class HoraField extends JTextField {

    public HoraField() {
        ((AbstractDocument) getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
                sb.insert(offset, text);

                if (isValidDate(sb.toString())) {
                    super.insertString(fb, offset, text, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
                sb.replace(offset, offset + length, text);

                if (isValidDate(sb.toString())) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            @Override
            public void remove(FilterBypass fb, int offset, int length) {
                // Do nothing
            }

            private boolean isValidDate(String text) {
                return text.matches("\\d{1,2}:\\d{1,2}");
            }
        });
    }

    /**
     * Constructor that sets the width of the component to {@code columns}
     * @param columns the integer to be set as the width of the component
     */
    public HoraField(int columns) {
        this();
        setColumns(columns);
    }
}
