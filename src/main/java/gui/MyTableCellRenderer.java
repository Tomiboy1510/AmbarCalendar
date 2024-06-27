package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Custom {@link DefaultTableCellRenderer} that sets the background and foreground colors of the cell.
 */
public class MyTableCellRenderer extends DefaultTableCellRenderer {

    public MyTableCellRenderer() {
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column
    ) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (isSelected) {
            setBackground(UiUtils.MAIN_COLOR);
            setForeground(Color.WHITE);
        } else {
            setForeground(Color.BLACK);
            // Alternating colors for better readability
            if (row % 2 == 0) {
                setBackground(UiUtils.GREYSCALE[0]);
            } else {
                setBackground(UiUtils.GREYSCALE[1]);
            }
        }

        return this;
    }
}
