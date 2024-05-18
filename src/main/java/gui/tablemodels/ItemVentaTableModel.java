package gui.tablemodels;

import entity.ItemVenta;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ItemVentaTableModel extends AbstractTableModel {

    private final List<ItemVenta> items;
    private final String[] columnNames = {
            "Producto",
            "Cantidad",
            "Monto"
    };

    public ItemVentaTableModel() {
        items = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ItemVenta item = items.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> item.getProducto();
            case 1 -> item.getCantidad();
            case 2 -> item.getMonto();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public List<ItemVenta> getItems() {
        return items;
    }

    public void add(ItemVenta item) {
        items.add(item);
        fireTableDataChanged();
    }

    public void remove(int rowIndex) {
        items.remove(rowIndex);
        fireTableDataChanged();
    }
}
