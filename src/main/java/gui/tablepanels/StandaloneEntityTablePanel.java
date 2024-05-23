package gui.tablepanels;

import entity.AbstractEntity;
import gui.tablemodels.StandaloneEntityTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class StandaloneEntityTablePanel<T extends AbstractEntity> extends MyTablePanel {

    protected final JButton addButton;
    protected final JButton modifyButton;
    protected final JButton removeButton;

    private final JLabel pageLabel;

    public StandaloneEntityTablePanel(String title, StandaloneEntityTableModel<T> tableModel) {
        super(tableModel);

        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.columnAtPoint(e.getPoint());
                column = table.convertColumnIndexToModel(column);
                tableModel.sortByColumn(column);
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

            String[] options = {"Sí", "No"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "¿Eliminar? (No se puede deshacer)",
                    "Eliminar " + title,
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);

            if (choice == JOptionPane.YES_OPTION) {
                @SuppressWarnings("rawtypes")
                StandaloneEntityTableModel model = ((StandaloneEntityTableModel) table.getModel());
                model.delete(selectedRow);
            }
        });

        JLabel titleLabel = new JLabel(title);
        titleLabel.setBorder(new EmptyBorder(15, 20, 8, 0));
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 25));
        add(titleLabel, BorderLayout.NORTH);

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

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
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
        StandaloneEntityTableModel<T> tableModel = ((StandaloneEntityTableModel<T>) table.getModel());
        pageLabel.setText("Página " + tableModel.getCurrentPage() + " de " + tableModel.getMaxPage());
    }
}