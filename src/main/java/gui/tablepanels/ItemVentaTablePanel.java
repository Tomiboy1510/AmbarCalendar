package gui.tablepanels;

import gui.tablemodels.ItemVentaTableModel;

import javax.swing.*;

public class ItemVentaTablePanel extends JPanel {

    private final JTable table;
    private final JButton addButton;
    private final JButton removeButton;
    private final JLabel montoTotalLabel;

    public ItemVentaTablePanel(ItemVentaTableModel tableModel) {

        table = new JTable(tableModel);
        addButton = new JButton("+");
        removeButton = new JButton("-");
        montoTotalLabel = new JLabel("Monto total: $0");
    }
}
