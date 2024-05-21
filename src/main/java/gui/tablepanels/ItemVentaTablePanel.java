package gui.tablepanels;

import entity.ItemVenta;
import gui.UiUtils;
import gui.tablemodels.ItemVentaTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ItemVentaTablePanel extends JPanel {

    private final JTable table;
    private final JLabel montoTotalLabel;

    public ItemVentaTablePanel(ItemVentaTableModel tableModel) {
        setLayout(new BorderLayout());

        table = new JTable(tableModel);
        JButton addButton = new JButton("+");
        JButton removeButton = new JButton("-");
        montoTotalLabel = new JLabel("Monto total: $0");

        addButton.setFocusable(false);
        removeButton.setFocusable(false);

        addButton.addActionListener(_ -> {
            ((ItemVentaTableModel) table.getModel()).add();
            updateMontoLabel();
        });

        removeButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            ((ItemVentaTableModel) table.getModel()).remove(selectedRow);
            updateMontoLabel();
        });

        table.setBackground(UiUtils.GREYSCALE[3]);
        table.getTableHeader().setBackground(UiUtils.GREYSCALE[2]);
        table.setSelectionBackground(UiUtils.GREYSCALE[1]);

        SwingUtilities.invokeLater(() -> table.setRowHeight(table.getTableHeader().getHeight()));

        table.setAutoCreateRowSorter(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane tablePanel = new JScrollPane(table);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel bottomRightPanel = new JPanel();

        tablePanel.setPreferredSize(new Dimension(500, 200));

        bottomRightPanel.add(addButton);
        bottomRightPanel.add(removeButton);
        bottomPanel.add(bottomRightPanel, BorderLayout.EAST);
        bottomPanel.add(montoTotalLabel, BorderLayout.WEST);

        tablePanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        add(tablePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void updateMontoLabel() {
        int montoTotal = ((ItemVentaTableModel) table.getModel())
                .getItems().stream()
                .mapToInt(ItemVenta::getMonto)
                .sum();
        montoTotalLabel.setText("Monto total: $" + montoTotal);
    }

    public List<ItemVenta> getItems() {
        return ((ItemVentaTableModel) table.getModel()).getItems();
    }
}
