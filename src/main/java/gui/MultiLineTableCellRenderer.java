package gui;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Custom {@link TableCellRenderer} used for showing text containing newline characters ('\n')
 * in multiple lines
 */
public class MultiLineTableCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column
    ) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(true);

        if (isSelected) {
            panel.setBackground(UiUtils.MAIN_COLOR);
            panel.setForeground(Color.WHITE);
        } else {
            panel.setForeground(Color.BLACK);
            // Alternating colors for better readability
            if (row % 2 == 0) {
                panel.setBackground(UiUtils.GREYSCALE[0]);
            } else {
                panel.setBackground(UiUtils.GREYSCALE[1]);
            }
        }

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;

        String text = (value == null) ? "" : value.toString();
        String[] lines = text.split("\n");
        int totalHeight = 0;

        for (String line : lines) {
            // Put each line of text in its own JLabel
            JLabel label = new JLabel(line);
            label.setFont(table.getFont());
            //label.setHorizontalAlignment(SwingConstants.CENTER);

            if (isSelected) {
                label.setForeground(Color.WHITE);
            } else {
                label.setForeground(Color.BLACK);
            }

            panel.add(label, constraints);
            constraints.gridy ++;
            totalHeight += label.getPreferredSize().height;
        }

        table.setRowHeight(row, totalHeight);
        return panel;
    }
}
