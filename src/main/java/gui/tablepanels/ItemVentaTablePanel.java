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

/**
 * GUI component used for displaying product sale items ({@link ItemVenta}) and allowing the user to
 * create new ones and modify or delete existing ones.
 */
public class ItemVentaTablePanel extends MyTablePanel {

    /**
     * Text label to show total price (sum of all item prices)
     */
    private final JLabel montoTotalLabel;

    public ItemVentaTablePanel(ItemVentaTableModel tableModel, ProductoDAO productoDAO, VentaForm ventaForm) {
        super(tableModel);

        // Buttons to add and remove items to the list
        JButton addButton = new JButton("+");
        JButton removeButton = new JButton("-");
        addButton.setFocusable(false);
        removeButton.setFocusable(false);

        montoTotalLabel = new JLabel("Monto total: $0");

        // Update montoTotalLabel when any change to the table data occurs
        tableModel.addDataChangedListener(_ -> updateMontoLabel());

        // Show form for new item
        addButton.addActionListener(_ -> {
            ItemVentaForm form = new ItemVentaForm(productoDAO, tableModel, ventaForm);
            // Transfer focus ownership to the item form
            ventaForm.removeFocusOwnership();
            form.giveFocusOwnership();
        });

        // Remove selected item
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

    /**
     * Sums prices of all items in the table and updates montoTotalLabel
     */
    private void updateMontoLabel() {
        int montoTotal = ((ItemVentaTableModel) table.getModel())
                .getItems().stream()
                .mapToInt(ItemVenta::getMonto)
                .sum();
        montoTotalLabel.setText("Monto total: $" + montoTotal);
    }

    /**
     * Retrieves all items in the table
     * @return a {@link List} of all {@link ItemVenta} objects shown in the table
     */
    public List<ItemVenta> getItems() {
        return ((ItemVentaTableModel) table.getModel()).getItems();
    }
}
