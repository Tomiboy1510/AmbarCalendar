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

/**
 * Form for creating items for a sale ({@link ItemVenta}).
 */
public class ItemVentaForm extends MyForm {

    private final JComboBox<Producto> productoField = new JComboBox<>();
    private final IntegerField cantidadField = new IntegerField(20);
    private final IntegerField precioUnitarioField = new IntegerField(20);

    /**
     * DAO to get all products of the business
     */
    private final ProductoDAO productoDAO;

    public ItemVentaForm(ProductoDAO productoDAO, ItemVentaTableModel tableModel, Focusable parent) {
        super("Añadir Item", parent);
        this.productoDAO = productoDAO;

        saveButton.addActionListener(_ -> {
            // Disable focus ownership so that the form doesn't close when losing focus to an error dialog
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

        // Set custom List Cell Renderer that shows a product in RED if stock is zero
        productoField.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus
            ) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Producto p = (Producto) value;
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

        // When a product is selected, set default unit price to that of the selected product
        productoField.addActionListener(_ -> {
            Producto p = (Producto) productoField.getSelectedItem();
            if (p != null)
                precioUnitarioField.setText(String.valueOf(p.getPrecio()));
        });

        // Set default quantity to 1
        cantidadField.setText("1");

        // Get all products from DAO and sort them by name
        productoDAO.getAll().stream()
                .sorted(Comparator.comparing(Producto::getNombre))
                .forEach(productoField::addItem);

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
