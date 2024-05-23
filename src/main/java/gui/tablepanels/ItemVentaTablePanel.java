package gui.tablepanels;

import entity.ItemVenta;
import gui.forms.ItemVentaForm;
import gui.tablemodels.ItemVentaTableModel;
import persistence.dao.ProductoDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ItemVentaTablePanel extends MyTablePanel {

    private final JLabel montoTotalLabel;

    public ItemVentaTablePanel(ItemVentaTableModel tableModel, ProductoDAO productoDAO) {
        super(tableModel);

        JButton addButton = new JButton("+");
        JButton removeButton = new JButton("-");
        addButton.setFocusable(false);
        removeButton.setFocusable(false);

        montoTotalLabel = new JLabel("Monto total: $0");

        addButton.addActionListener(_ -> {
            new ItemVentaForm(productoDAO, tableModel);
        });

        removeButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            ((ItemVentaTableModel) table.getModel()).remove(selectedRow);
            updateMontoLabel();
        });

        SwingUtilities.invokeLater(() -> table.setRowHeight(table.getTableHeader().getHeight()));

        JPanel bottomRightPanel = new JPanel();
        bottomRightPanel.add(addButton);
        bottomRightPanel.add(removeButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(bottomRightPanel, BorderLayout.EAST);
        bottomPanel.add(montoTotalLabel, BorderLayout.WEST);
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

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
