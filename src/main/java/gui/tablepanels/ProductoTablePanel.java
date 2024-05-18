package gui.tablepanels;

import entity.Producto;
import gui.forms.ProductoForm;
import gui.tablemodels.ProductoTableModel;
import persistence.dao.ProductoDAO;

public class ProductoTablePanel extends EntityTablePanel<Producto> {

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
    }
}
