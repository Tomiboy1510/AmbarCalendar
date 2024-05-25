package gui.forms;

import entity.ItemVenta;
import entity.Producto;
import gui.Focusable;
import gui.formattedfields.IntegerField;
import gui.tablemodels.ItemVentaTableModel;
import persistence.dao.ProductoDAO;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;

public class ItemVentaForm extends MyForm {

    private final JComboBox<Producto> productoField = new JComboBox<>();
    private final IntegerField cantidadField = new IntegerField(20);
    private final IntegerField precioUnitarioField = new IntegerField(20);

    private final ProductoDAO productoDAO;

    public ItemVentaForm(ProductoDAO productoDAO, ItemVentaTableModel tableModel, Focusable parent) {
        super("Añadir Item", parent);
        this.productoDAO = productoDAO;

        saveButton.addActionListener(_ -> {
            setHasFocusOwnership(false);
            ItemVenta item = buildItemVenta();
            setHasFocusOwnership(true);
            if (item != null) {
                tableModel.add(item);
                removeFocusOwnership();
                dispose();
            }
        });

        init();
    }

    protected void init() {
        addField("Producto", productoField);
        addField("Cantidad", cantidadField);
        addField("Precio por unidad", precioUnitarioField);

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

        productoField.addActionListener(_ -> {
            Producto p = (Producto) productoField.getSelectedItem();
            if (p != null)
                precioUnitarioField.setText(String.valueOf(p.getPrecio()));
        });

        cantidadField.setText("1");

        productoDAO.getAll().stream()
                .sorted(Comparator.comparing(Producto::getNombre))
                .forEach(productoField::addItem);

        Producto selectedProducto = ((Producto) productoField.getSelectedItem());
        if (selectedProducto != null)
            precioUnitarioField.setText(String.valueOf(selectedProducto.getPrecio()));

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
            JOptionPane.showMessageDialog(null, "Cantidad debe ser positiva", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        try {
            item.setMonto(item.getCantidad() * Integer.parseInt(precioUnitarioField.getText()));
            if (item.getMonto() < 0)
                throw new Exception();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Precio unitario obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Precio unitario no puede ser negativo", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return item;
    }
}
