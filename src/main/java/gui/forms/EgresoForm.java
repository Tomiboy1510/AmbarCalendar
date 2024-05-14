package gui.forms;

import entity.Egreso;
import persistence.dao.EgresoDAO;

import javax.swing.*;

public class EgresoForm extends EntityForm {

    private final JTextField motivoField = new JTextField(20);
    private final JTextField montoField = new JTextField(20);
    private final JTextField fechaField = new JTextField(20);

    public EgresoForm(EgresoDAO dao) {
        super("Registrar Egreso", dao);
        init();

        // Set today as default date
    }

    public EgresoForm(Egreso e, EgresoDAO dao) {
        super("Modificar Egreso", dao);
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
    protected Egreso buildEntity() {
        return null;
    }
}
