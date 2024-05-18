package gui.tablemodels;

import entity.Producto;
import persistence.dao.ProductoDAO;

public class ProductoTableModel extends EntityTableModel<Producto> {

    public ProductoTableModel(ProductoDAO dao) {
        super(dao, new String[] {
                "Nombre",
                "Marca",
                "Costo",
                "Precio",
                "Stock"},
        80);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto p = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getNombre();
            case 1 -> p.getMarca();
            case 2 -> p.getCosto();
            case 3 -> p.getPrecio();
            case 4 -> p.getStock();
            default -> null;
        };
    }
}
