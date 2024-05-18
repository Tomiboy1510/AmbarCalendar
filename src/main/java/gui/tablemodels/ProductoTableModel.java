package gui.tablemodels;

import entity.Producto;
import persistence.dao.ProductoDAO;

public class ProductoTableModel extends EntityTableModel<Producto> {

    public ProductoTableModel(ProductoDAO dao) {
        super(dao, Producto.getFieldNamesButPretty(), Producto.getFieldNames(), 80);
    }
}
