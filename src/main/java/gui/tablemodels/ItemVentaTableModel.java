package gui.tablemodels;

import entity.ItemVenta;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ItemVentaTableModel extends AbstractTableModel {

    private final List<ItemVenta> data;
    private final String[] columnNames = {"Producto", "Cantidad", "Monto"};

    public ItemVentaTableModel() {
        data = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
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
}
