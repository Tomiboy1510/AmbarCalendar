package gui.forms;

import entity.VentaProducto;

import java.awt.event.ActionListener;

public class VentaProductoForm extends EntityForm {

    public VentaProductoForm() {
        super("Registrar Turno");
    }

    public VentaProductoForm(VentaProducto v) {
        super("Modificar Turno");
    }

    private void init() {


        afterInit();
    }

    @Override
    protected ActionListener getSaveButtonHandler() {
        return null;
    }
}
