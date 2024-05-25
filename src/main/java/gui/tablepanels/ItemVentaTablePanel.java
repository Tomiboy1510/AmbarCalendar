package gui.tablepanels;

import entity.ItemVenta;
import gui.forms.ItemVentaForm;
import gui.forms.VentaForm;
import gui.tablemodels.ItemVentaTableModel;
import persistence.dao.ProductoDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ItemVentaTablePanel extends MyTablePanel {

    private final JLabel montoTotalLabel;

    public ItemVentaTablePanel(ItemVentaTableModel tableModel, ProductoDAO productoDAO, VentaForm ventaForm) {
        super(tableModel);

        JButton addButton = new JButton("+");
        JButton removeButton = new JButton("-");
        addButton.setFocusable(false);
        removeButton.setFocusable(false);

        montoTotalLabel = new JLabel("Monto total: $0");

        tableModel.addDataChangedListener(_ -> updateMontoLabel());

        addButton.addActionListener(_ -> {
            ItemVentaForm form = new ItemVentaForm(productoDAO, tableModel, ventaForm);
            ventaForm.removeFocusOwnership();
            form.giveFocusOwnership();
        });

        removeButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            tableModel.remove(selectedRow);
        });

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
