package gui.tablepanels;

import entity.AbstractEntity;
import gui.UiUtils;
import gui.tablemodels.EntityTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class EntityTablePanel<T extends AbstractEntity> extends JPanel {

    protected final JTable table;
    protected final JButton addButton;
    protected final JButton modifyButton;
    protected final JButton removeButton;
    private final JLabel pageLabel;

    public EntityTablePanel(String title, EntityTableModel<T> tableModel) {
        setLayout(new BorderLayout());

        table = new JTable(tableModel);

        table.setBackground(UiUtils.GREYSCALE[3]);
        table.getTableHeader().setBackground(UiUtils.GREYSCALE[2]);
        table.setSelectionBackground(UiUtils.GREYSCALE[1]);

        table.setAutoCreateRowSorter(false);
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.columnAtPoint(e.getPoint());
                column = table.convertColumnIndexToModel(column);
                tableModel.sortByColumn(column);
            }
        });

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(SwingConstants.CENTER);
            }
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column
            ) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    setForeground(Color.BLACK);
                } else {
                    setBackground(table.getBackground());
                }
                return this;
            }
        });

        addButton = new JButton("Añadir");
        modifyButton = new JButton("Modificar");
        removeButton = new JButton("Eliminar");

        addButton.setFocusable(false);
        modifyButton.setFocusable(false);
        removeButton.setFocusable(false);

        removeButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            @SuppressWarnings("rawtypes")
            EntityTableModel model = ((EntityTableModel) table.getModel());
            model.delete(selectedRow);
        });

        JScrollPane tablePanel = new JScrollPane(table);
        JPanel bottomPanel = new JPanel(new BorderLayout());

        tablePanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setBorder(new EmptyBorder(15, 20, 8, 0));
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 25));

        add(titleLabel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        JButton prevPageButton = new JButton("-");
        JButton nextPageButton = new JButton("+");
        prevPageButton.setFocusable(false);
        nextPageButton.setFocusable(false);

        JPanel bottomLeftPanel = new JPanel();
        JPanel bottomRightPanel = new JPanel();

        pageLabel = new JLabel();
        bottomLeftPanel.add(prevPageButton);
        bottomLeftPanel.add(pageLabel);
        bottomLeftPanel.add(nextPageButton);

        bottomRightPanel.add(addButton);
        bottomRightPanel.add(modifyButton);
        bottomRightPanel.add(removeButton);

        bottomPanel.add(bottomLeftPanel, BorderLayout.WEST);
        bottomPanel.add(bottomRightPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        prevPageButton.addActionListener(_ -> {
            tableModel.previousPage();
            updatePageLabel();
        });

        nextPageButton.addActionListener(_ -> {
            tableModel.nextPage();
            updatePageLabel();
        });

        updatePageLabel();
    }

    private void updatePageLabel() {
        @SuppressWarnings("unchecked")
        EntityTableModel<T> tableModel = ((EntityTableModel<T>) table.getModel());
        pageLabel.setText("Página " + tableModel.getCurrentPage() + " de " + tableModel.getMaxPage());
    }
}
