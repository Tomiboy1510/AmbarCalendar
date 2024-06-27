package gui.tablemodels;

import entity.ItemVenta;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Table model to manage and display product sale items ({@link ItemVenta}) in a {@link JTable}.
 */
public class ItemVentaTableModel extends EntityTableModel {

    /**
     * List of items
     */
    private final List<ItemVenta> data;

    /**
     * List of {@link ActionListener} objects to be invoked when a change in data occurs
     */
    private final List<ActionListener> dataChangedListeners;

    public ItemVentaTableModel() {
        super(new String[] {"Producto", "Cantidad", "Monto"});
        data = new ArrayList<>();
        dataChangedListeners = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ItemVenta i = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> i.getProducto().getNombre();
            case 1 -> i.getCantidad();
            case 2 -> i.getMonto();
            default -> null;
        };
    }

    /**
     * Add item to the data
     * @param item the item to be added
     */
    public void add(ItemVenta item) {
        data.add(item);
        fireTableDataChanged();
        dataChangedListeners.forEach(l -> l.actionPerformed(null));
    }

    /**
     * Remove item from the data
     * @param rowIndex the row index of the item to be removed
     */
    public void remove(int rowIndex) {
        data.remove(rowIndex);
        fireTableDataChanged();
        dataChangedListeners.forEach(l -> l.actionPerformed(null));
    }

    public List<ItemVenta> getItems() {
        return data;
    }

    @Override
    public ItemVenta getEntityAtRow(int rowIndex) {
        return data.get(rowIndex);
    }

    /**
     * Add action listener to {@code dataChangedListeners}
     * @param listener the {@link ActionListener} to be added
     */
    public void addDataChangedListener(ActionListener listener) {
        dataChangedListeners.add(listener);
    }
}
