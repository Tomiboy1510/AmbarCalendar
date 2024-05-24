package gui.tablepanels;

import entity.Producto;
import gui.MyTableCellRenderer;
import gui.forms.ProductoForm;
import gui.tablemodels.ProductoTableModel;
import persistence.dao.ProductoDAO;

import javax.swing.*;
import java.awt.*;

public class ProductoTablePanel extends StandaloneEntityTablePanel<Producto> {

    public ProductoTablePanel(ProductoDAO dao) {
        super("Productos", new ProductoTableModel(dao));

        addButton.addActionListener(_ -> new ProductoForm(dao));

        removeButton.setVisible(false);

        modifyButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            ProductoTableModel model = ((ProductoTableModel) table.getModel());
            new ProductoForm(model.getEntityAtRow(selectedRow), dao);
        });

        table.setDefaultRenderer(Object.class, new MyTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column
            ) {
                Component res = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ProductoTableModel tableModel = ((ProductoTableModel) table.getModel());
                if ((! isSelected) && tableModel.getEntityAtRow(row).getStock() <= 0)
                    setForeground(Color.RED);
                return res;
            }
        });
    }
}
