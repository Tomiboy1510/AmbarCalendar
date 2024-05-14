package gui.forms;

import entity.Producto;
import persistence.dao.ProductoDAO;

import javax.swing.*;

public class ProductoForm extends EntityForm {

    private final JTextField nombreField = new JTextField(20);
    private final JTextField marcaField = new JTextField(20);
    private final JTextField costoField = new JTextField(20);
    private final JTextField precioField = new JTextField(20);
    private final JTextField stockField = new JTextField(20);

    public ProductoForm(ProductoDAO dao) {
        super("Añadir Producto", dao);
        init();
    }

    public ProductoForm(Producto p, ProductoDAO dao) {
        super("Modificar Producto", dao);
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
    protected Producto buildEntity() {
        return null;
    }
}
