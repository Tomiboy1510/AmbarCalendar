package gui.tablemodels;

import entity.ItemVenta;
import entity.Producto;
import gui.formattedfields.IntegerField;
import persistence.dao.ProductoDAO;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ItemVentaTableModel extends AbstractTableModel {

    private final List<Row> rows;
    private final List<Producto> productos;
    private final String[] columnNames = {
            "Producto",
            "Cantidad",
            "Monto"
    };

    public ItemVentaTableModel(ProductoDAO productoDAO) {
        productos = productoDAO.getAll();
        rows = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Row r = rows.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> r.productoField;
            case 1 -> r.cantidadField;
            case 2 -> r.montoField;
            default -> null;
        };
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public List<ItemVenta> getItems() {
        return rows.stream()
                .map(Row::toItem)
                .toList();
    }

    public void add() {
        rows.add(new Row());
        fireTableDataChanged();
    }

    public void remove(int rowIndex) {
        rows.remove(rowIndex);
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Row r = rows.get(rowIndex);
        switch (columnIndex) {
            case 0 -> r.productoField = (JComboBox<Producto>) value;
            case 1 -> r.cantidadField = (JSpinner) value;
            case 2 -> r.montoField = (IntegerField) value;
            default -> {}
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> JComboBox.class;
            case 1 -> JSpinner.class;
            case 2 -> IntegerField.class;
            default -> Object.class;
        };
    }

    private class Row {

        JComboBox<Producto> productoField;
        JSpinner cantidadField;
        IntegerField montoField;

        public Row() {
            productoField = new JComboBox<>(productos.toArray(new Producto[0]));
            productoField.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(
                        JList<?> list, Object value, int index,
                        boolean isSelected, boolean cellHasFocus
                ) {
                    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    Producto p = ((Producto) value);
                    if (p != null)
                        label.setText(p.getNombre());
                    return label;
                }
            });
            cantidadField = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
            montoField = new IntegerField();
            montoField.setText("1");
        }

        public ItemVenta toItem() {
            ItemVenta i = new ItemVenta();
            i.setProducto(((Producto) productoField.getSelectedItem()));
            i.setCantidad(((int) cantidadField.getValue()));
            try {
                i.setMonto(Integer.parseInt(montoField.getText()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Monto obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return i;
        }
    }
}
