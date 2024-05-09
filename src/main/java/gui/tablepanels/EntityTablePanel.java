package gui.tablepanels;

import gui.UiUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class EntityTablePanel extends JPanel {

    private final TableModel tableModel;
    private final JButton addButton;
    private final JButton modifyButton;
    private final JButton removeButton;

    public EntityTablePanel(String title, TableModel model) {
        setLayout(new BorderLayout());

        tableModel = model;
        JTable table = new JTable(model);
        table.setBackground(UiUtils.GREYSCALE[3]);
        table.getTableHeader().setBackground(UiUtils.GREYSCALE[2]);
        table.setSelectionBackground(UiUtils.GREYSCALE[1]);

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

        addButton.addActionListener(getAddButtonHandler());
        modifyButton.addActionListener(getModifyButtonHandler());
        removeButton.addActionListener(getRemoveButtonHandler());
    }

    protected abstract ActionListener getAddButtonHandler();
    protected abstract ActionListener getModifyButtonHandler();
    protected abstract ActionListener getRemoveButtonHandler();
}
