package gui.tablemodels;

import entity.Producto;
import persistence.dao.ProductoDAO;

public class ProductoTableModel extends StandaloneEntityTableModel<Producto> {

    public ProductoTableModel(ProductoDAO dao) {
        super(dao, new String[] {"Nombre", "Marca", "Costo", "Precio", "Stock"}, 80);
    }

    @Override
    public void sortByColumn(int columnIndex) {
        if (sortingColumn == columnIndex)
            sortAscending = (! sortAscending);
        ProductoDAO dao = ((ProductoDAO) this.dao);
        switch (columnIndex) {
            case 0 -> dao.sortByNombre(sortAscending);
            case 1 -> dao.sortByMarca(sortAscending);
            case 2 -> dao.sortByCosto(sortAscending);
            case 3 -> dao.sortByPrecio(sortAscending);
            case 4 -> dao.sortByStock(sortAscending);
            default -> {return;}
        }
        sortingColumn = columnIndex;
        refresh();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto p = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getNombre();
            case 1 -> p.getMarca();
            case 2 -> "$" + p.getCosto();
            case 3 -> "$" + p.getPrecio();
            case 4 -> p.getStock();
            default -> null;
        };
    }
}
