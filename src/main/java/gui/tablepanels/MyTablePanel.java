package gui.tablepanels;

import gui.MyTableCellRenderer;
import gui.UiUtils;
import gui.tablemodels.EntityTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Generic GUI component that shows a table.
 */
public abstract class MyTablePanel extends JPanel {

    /**
     * {@link JTable} used for displaying entities in each row
     */
    protected final JTable table;

    public MyTablePanel(EntityTableModel tableModel, boolean showToolTips) {
        setLayout(new BorderLayout());

        if (showToolTips) {
            table = new JTable(tableModel) {
                // Show full value of a cell when hovering the cursor over it
                @Override
                public String getToolTipText(MouseEvent e) {
                    int rowIndex = rowAtPoint(e.getPoint());
                    int colIndex = columnAtPoint(e.getPoint());
                    if (rowIndex < 0 || colIndex < 0)
                        return null;
                    Object value = getValueAt(rowIndex, colIndex);
                    if (value != null)
                        return value.toString();
                    return null;
                }

                // Tooltip text follows the cursor at all times
                @Override
                public Point getToolTipLocation(MouseEvent e) {
                    Point mousePoint = e.getPoint();
                    mousePoint.translate(10, 10);
                    return mousePoint;
                }
            };
        } else {
            table = new JTable(tableModel);
        }

        // Set colors
        table.setBackground(UiUtils.GREYSCALE[3]);
        table.getTableHeader().setBackground(UiUtils.GREYSCALE[2]);
        table.setSelectionBackground(UiUtils.GREYSCALE[1]);

        // Custom sorting logic will be used instead
        table.setAutoCreateRowSorter(false);

        // Can select only one row at a time
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Use custom default cell renderer
        table.setDefaultRenderer(Object.class, new MyTableCellRenderer());

        JScrollPane tablePanel = new JScrollPane(table);
        tablePanel.setPreferredSize(new Dimension(500, 200));
        tablePanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        add(tablePanel, BorderLayout.CENTER);
    }
}
