package gui.forms;

import entity.Producto;
import gui.formattedfields.IntegerField;
import persistence.dao.ProductoDAO;

import javax.swing.*;

public class ProductoForm extends EntityForm {

    private final JTextField nombreField = new JTextField(20);
    private final JTextField marcaField = new JTextField(20);
    private final IntegerField costoField = new IntegerField(20);
    private final IntegerField precioField = new IntegerField(20);
    private final IntegerField stockField = new IntegerField(20);

    public ProductoForm(ProductoDAO dao) {
        super("Añadir Producto", dao);
        init();
    }

    public ProductoForm(Producto p, ProductoDAO dao) {
        super("Modificar Producto", dao);
        isNew = false;

        nombreField.setText(p.getNombre());
        marcaField.setText(p.getMarca());
        costoField.setText(String.valueOf(p.getCosto()));
        precioField.setText(String.valueOf(p.getPrecio()));
        stockField.setText(String.valueOf(p.getStock()));

        init();
    }

    private void init() {
        addField("Nombre", nombreField);
        addField("Marca", marcaField);
        addField("Costo", costoField);
        addField("Precio", precioField);
        addField("Stock", stockField);

        afterInit();
    }

    @Override
    protected Producto buildEntity() {
        Producto p = new Producto();
        p.setNombre(nombreField.getText());
        p.setMarca(marcaField.getText());
        try {
            p.setCosto(Integer.parseInt(costoField.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Costo obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        try {
            p.setPrecio(Integer.parseInt(precioField.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Precio obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        try {
            p.setStock(Integer.parseInt(stockField.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Stock obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return p;
    }
}
