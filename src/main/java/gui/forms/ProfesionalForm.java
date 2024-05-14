package gui.forms;

import entity.Profesional;
import persistence.dao.ProfesionalDAO;

import javax.swing.*;

public class ProfesionalForm extends EntityForm {

    private final JTextField nombreField = new JTextField(20);
    private final JTextField porcentajeField = new JTextField(20);
    private final JTextField salarioField = new JTextField(20);

    public ProfesionalForm(ProfesionalDAO dao) {
        super("Añadir Profesional", dao);
        init();
    }

    public ProfesionalForm(Profesional p, ProfesionalDAO dao) {
        super("Modificar Profesional", dao);
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
    protected Profesional buildEntity() {
        return null;
    }
}
