package gui.tablepanels;

import entity.Producto;
import gui.forms.ProductoForm;
import gui.tablemodels.ProductoTableModel;
import persistence.dao.ProductoDAO;

public class ProductoTablePanel extends EntityTablePanel<Producto> {

    public ProductoTablePanel(ProductoDAO dao) {
        super("Productos", new ProductoTableModel(dao));

        addButton.addActionListener(_ -> openForm(new ProductoForm(dao)));

        modifyButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            ProductoTableModel model = ((ProductoTableModel) table.getModel());
            openForm(new ProductoForm(model.getEntityAtRow(selectedRow), dao));
        });
    }
}
