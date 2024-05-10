package gui.forms;

import entity.Producto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductoForm extends EntityForm {

    private final JTextField nombreField = new JTextField(20);
    private final JTextField marcaField = new JTextField(20);
    private final JTextField costoField = new JTextField(20);
    private final JTextField precioField = new JTextField(20);
    private final JTextField stockField = new JTextField(20);
    private boolean isNew = true;

    public ProductoForm() {
        super("Añadir Producto");
        init();
    }

    public ProductoForm(Producto p) {
        super("Modificar Producto");
        isNew = false;
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
    protected ActionListener getSaveButtonHandler() {
        return (ActionEvent e) -> {

        };
    }
}
