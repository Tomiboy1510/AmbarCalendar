package gui.tablepanels;

import gui.UiUtils;
import gui.tablemodels.EntityTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class EntityTablePanel<T> extends JPanel {

    protected final JTable table;
    protected final JButton addButton;
    protected final JButton modifyButton;
    protected final JButton removeButton;

    public EntityTablePanel(String title, EntityTableModel<T> tableModel) {
        setLayout(new BorderLayout());

        table = new JTable(tableModel);
        table.setBackground(UiUtils.GREYSCALE[3]);
        table.getTableHeader().setBackground(UiUtils.GREYSCALE[2]);
        table.setSelectionBackground(UiUtils.GREYSCALE[1]);
        table.setRowSorter(tableModel.getSorter());

        addButton = new JButton("Añadir");
        modifyButton = new JButton("Modificar");
        removeButton = new JButton("Eliminar");

        addButton.setFocusable(false);
        modifyButton.setFocusable(false);
        removeButton.setFocusable(false);

        JScrollPane tablePanel = new JScrollPane(table);
        JPanel bottomPanel = new JPanel();

        tablePanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setBorder(new EmptyBorder(15, 20, 8, 0));
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 25));

        add(titleLabel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        bottomPanel.add(addButton);
        bottomPanel.add(modifyButton);
        bottomPanel.add(removeButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
