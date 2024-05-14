package gui.tablepanels;

import entity.Producto;
import gui.forms.EntityForm;
import gui.forms.ProductoForm;
import gui.tablemodels.ProductoTableModel;
import persistence.dao.ProductoDAO;

public class ProductoTablePanel extends EntityTablePanel<Producto> {

    public ProductoTablePanel(ProductoDAO dao) {
        super("Productos", new ProductoTableModel(dao));

        addButton.addActionListener(_ -> {
            EntityForm form = new ProductoForm(dao);
            form.setVisible(true);
        });

        modifyButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            ProductoTableModel model = ((ProductoTableModel) table.getModel());
            EntityForm form = new ProductoForm(model.getEntityAtRow(selectedRow), dao);
            form.setVisible(true);
        });

        removeButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            ProductoTableModel model = ((ProductoTableModel) table.getModel());
            model.delete(selectedRow);
        });
    }
}
