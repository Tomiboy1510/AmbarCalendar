package gui.formattedfields;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * Custom JTextField to input integers. Only allows digits and (optionally) a negative sign at the start.
 */
public class IntegerField extends JTextField {

    public IntegerField() {
        ((AbstractDocument) getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                StringBuilder sb = new StringBuilder();
                for (char c : string.toCharArray()) {
                    if (Character.isDigit(c) || (c == '-' && sb.isEmpty() && offset == 0)) {
                        sb.append(c);
                    }
                }
                super.insertString(fb, offset, sb.toString(), attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (length > 0) fb.remove(offset, length);
                insertString(fb, offset, text, attrs);
            }
        });
    }

    /**
     * Constructor that sets the width of the component to {@code columns}
     * @param columns the integer to be set as the width of the component
     */
    public IntegerField(int columns) {
        this();
        setColumns(columns);
    }
}
