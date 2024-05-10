package gui.forms;

import entity.Egreso;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EgresoForm extends EntityForm {

    private final JTextField motivoField = new JTextField(20);
    private final JTextField montoField = new JTextField(20);
    private final JTextField fechaField = new JTextField(20);
    private boolean isNew = true;

    public EgresoForm() {
        super("Registrar Egreso");
        init();

        // Set today as default date
    }

    public EgresoForm(Egreso e) {
        super("Modificar Egreso");
        isNew = false;
        init();
    }

    private void init() {
        addField("Motivo", motivoField);
        addField("Monto", montoField);
        addField("Fecha", fechaField);

        afterInit();
    }

    @Override
    protected ActionListener getSaveButtonHandler() {
        return null;
    }
}
