package gui.tablemodels;

import entity.ItemVenta;

import java.util.ArrayList;
import java.util.List;

public class ItemVentaTableModel extends EntityTableModel {

    private final List<ItemVenta> data;

    public ItemVentaTableModel() {
        super(new String[] {"Producto", "Cantidad", "Monto"});
        data = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        ItemVenta i = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> i.getProducto().getNombre();
            case 1 -> i.getCantidad();
            case 2 -> i.getMonto();
            default -> null;
        };
    }

    public void add(ItemVenta item) {
        data.add(item);
    }

    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }

    public List<ItemVenta> getItems() {
        return data;
    }

    @Override
    public ItemVenta getEntityAtRow(int rowIndex) {
        return data.get(rowIndex);
    }
}
