package gui.forms;

import entity.VentaServicio;

import java.awt.event.ActionListener;

public class VentaServicioForm extends EntityForm {

    public VentaServicioForm() {
        super("Registrar Turno");
    }

    public VentaServicioForm(VentaServicio v) {
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
