package gui.tablepanels;

import entity.Producto;
import gui.MyTableCellRenderer;
import gui.forms.ProductoForm;
import gui.tablemodels.ProductoTableModel;
import persistence.dao.ProductoDAO;

import javax.swing.*;
import java.awt.*;

/**
 * GUI component used for displaying products ({@link Producto}) and allowing the user to
 * create new ones and modify or delete existing ones.
 */
public class ProductoTablePanel extends StandaloneEntityTablePanel<Producto> {

    public ProductoTablePanel(ProductoDAO dao) {
        super("Productos", new ProductoTableModel(dao));

        // Show form for new product
        addButton.addActionListener(_ -> new ProductoForm(dao));

        // Does not allow removing (another entity may depend on this one)
        removeButton.setVisible(false);

        // Show form for modifying
        modifyButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            ProductoTableModel model = ((ProductoTableModel) table.getModel());
            new ProductoForm(model.getEntityAtRow(selectedRow), dao);
        });

        // Set custom default cell renderer
        table.setDefaultRenderer(Object.class, new MyTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column
            ) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Show text in red if stock value is zero or less
                ProductoTableModel tableModel = ((ProductoTableModel) table.getModel());
                if ((! isSelected) && tableModel.getEntityAtRow(row).getStock() <= 0)
                    setForeground(Color.RED);

                return this;
            }
        });
    }
}
