package gui.forms;

import entity.ItemVenta;
import entity.Producto;
import gui.formattedfields.IntegerField;
import gui.tablemodels.ItemVentaTableModel;
import persistence.dao.ProductoDAO;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;

public class ItemVentaForm extends MyForm {

    private final JComboBox<Producto> productoField = new JComboBox<>();
    private final IntegerField cantidadField = new IntegerField(20);
    private final IntegerField montoField = new IntegerField(20);

    private final ProductoDAO productoDAO;

    public ItemVentaForm(ProductoDAO productoDAO, ItemVentaTableModel tableModel) {
        super("Añadir Item");
        this.productoDAO = productoDAO;

        saveButton.addActionListener(_ -> {
            ItemVenta item = buildItemVenta();
            if (item != null) {
                tableModel.add(item);
                dispose();
            }
        });

        init();
    }

    protected void init() {
        addField("Producto", productoField);
        addField("Cantidad", cantidadField);
        addField("Monto", montoField);

        productoField.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus
            ) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Producto p = ((Producto) value);
                if (p != null) {
                    String labelText = p.getNombre();
                    if (p.getStock() <= 0) {
                        labelText += " (Sin stock)";
                        if (! isSelected)
                            label.setForeground(Color.RED);
                    }
                    label.setText(labelText);
                }
                return label;
            }
        });

        cantidadField.setText("1");

        productoDAO.getAll().stream()
                .sorted(Comparator.comparing(Producto::getNombre))
                .forEach(productoField::addItem);

        Producto selectedProducto = ((Producto) productoField.getSelectedItem());
        if (selectedProducto != null)
            montoField.setText(String.valueOf(selectedProducto.getPrecio()));

        afterInit();
    }

    protected ItemVenta buildItemVenta() {
        ItemVenta item = new ItemVenta();
        item.setProducto(((Producto) productoField.getSelectedItem()));
        try {
            item.setCantidad(Integer.parseInt(cantidadField.getText()));
            if (item.getCantidad() <= 0)
                throw new Exception();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad obligatoria", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cantidad de un item debe ser positiva", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        try {
            item.setMonto(Integer.parseInt(montoField.getText()));
            if (item.getMonto() < 0)
                throw new Exception();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Monto obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Monto no puede ser negativo", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return item;
    }
}
