package gui.forms;

import entity.Venta;
import persistence.dao.VentaDAO;

import javax.swing.*;

public class VentaForm extends IngresoForm {

    private final JTextField itemsField = new JTextField(20);

    public VentaForm(VentaDAO dao) {
        super("Registrar Venta", dao);
        init();
    }

    public VentaForm(Venta v, VentaDAO dao) {
        super("Modificar Venta", dao);
        isNew = false;
        init();
    }

    protected void init() {
        addField("Items", itemsField);
        super.init();
    }

    @Override
    protected Venta buildEntity() {
        return null;
    }
}
