package gui.forms;

import entity.AbstractEntity;
import entity.Producto;
import gui.formattedfields.IntegerField;
import persistence.dao.ProductoDAO;

import javax.swing.*;

public class ItemVentaForm extends EntityForm {

    private final JComboBox<Producto> productoField = new JComboBox<>();
    private final IntegerField cantidadField = new IntegerField(20);
    private final IntegerField montoField = new IntegerField(20);

    private final ProductoDAO productoDAO;

    public ItemVentaForm(ProductoDAO productoDAO) {
        super("Añadir Item", null);
        this.productoDAO = productoDAO;

        init();
    }

    protected void init() {
        addField("Producto", productoField);
        addField("Cantidad", cantidadField);
        addField("Monto", montoField);

        afterInit();
    }

    @Override
    protected AbstractEntity buildEntity() {
        return null;
    }
}
