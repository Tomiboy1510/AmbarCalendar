package gui.tablepanels;

import gui.MyTableCellRenderer;
import gui.UiUtils;
import gui.tablemodels.EntityTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class MyTablePanel extends JPanel {

    protected final JTable table;

    public MyTablePanel(EntityTableModel tableModel) {
        setLayout(new BorderLayout());

        table = new JTable(tableModel) {
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

            @Override
            public Point getToolTipLocation(MouseEvent e) {
                Point mousePoint = e.getPoint();
                mousePoint.translate(10, 10);
                return mousePoint;
            }
        };
        table.setBackground(UiUtils.GREYSCALE[3]);
        table.getTableHeader().setBackground(UiUtils.GREYSCALE[2]);
        table.setSelectionBackground(UiUtils.GREYSCALE[1]);
        table.setAutoCreateRowSorter(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultRenderer(Object.class, new MyTableCellRenderer());

        JScrollPane tablePanel = new JScrollPane(table);
        tablePanel.setPreferredSize(new Dimension(500, 200));
        tablePanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        add(tablePanel, BorderLayout.CENTER);
    }
}
