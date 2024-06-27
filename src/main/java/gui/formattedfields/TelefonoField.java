package gui.formattedfields;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * Custom JTextField to input telephone numbers. It only allows digits, spaces and plus and minus signs.
 * (It doesn't check that the text is a valid phone number, I just wanted to limit the sort of characters accepted)
 */
public class TelefonoField extends JTextField {

    public TelefonoField() {
        ((AbstractDocument) getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                StringBuilder sb = new StringBuilder();
                for (char c : string.toCharArray()) {
                    if (Character.isDigit(c) || "+- ".contains(String.valueOf(c))) {
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
    public TelefonoField(int columns) {
        this();
        setColumns(columns);
    }
}
