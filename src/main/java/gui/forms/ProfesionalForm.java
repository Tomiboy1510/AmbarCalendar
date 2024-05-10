package gui.forms;

import entity.Profesional;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ProfesionalForm extends EntityForm {

    private final JTextField nombreField = new JTextField(20);
    private final JTextField porcentajeField = new JTextField(20);
    private final JTextField salarioField = new JTextField(20);
    private boolean isNew = true;

    public ProfesionalForm() {
        super("Añadir Profesional");
        init();
    }

    public ProfesionalForm(Profesional p) {
        super("Modificar Profesional");
        isNew = false;
        init();
    }

    private void init() {
        addField("Nombre", nombreField);
        addField("Porcentaje de cobro", porcentajeField);
        addField("Salario básico", salarioField);

        afterInit();
    }

    @Override
    protected ActionListener getSaveButtonHandler() {
        return null;
    }
}
