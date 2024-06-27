package gui.formattedfields;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * Custom JTextField to input float numbers. Only allows digits and (optionally) a decimal point and/or a negative sign.
 */
public class FloatField extends JTextField {

    public FloatField() {
        ((AbstractDocument) getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                StringBuilder sb = new StringBuilder();
                for (char c : string.toCharArray()) {
                    switch (c) {
                        case '-':
                            if (sb.isEmpty() && offset == 0)
                                sb.append('-');
                            break;
                        case ',':
                        case '.':
                            if (! getText().contains(".")) {
                                sb.append('.');
                            }
                            break;
                        default:
                            if (Character.isDigit(c))
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
    public FloatField(int columns) {
        this();
        setColumns(columns);
    }
}
