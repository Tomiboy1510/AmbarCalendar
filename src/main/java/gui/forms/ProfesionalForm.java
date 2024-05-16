package gui.forms;

import entity.Profesional;
import gui.formattedfields.IntegerField;
import gui.formattedfields.FloatField;
import persistence.dao.ProfesionalDAO;

import javax.swing.*;

public class ProfesionalForm extends EntityForm {

    private final JTextField nombreField = new JTextField(20);
    private final FloatField porcentajeField = new FloatField(20);
    private final IntegerField salarioField = new IntegerField(20);

    public ProfesionalForm(ProfesionalDAO dao) {
        super("Añadir Profesional", dao);
        init();
    }

    public ProfesionalForm(Profesional p, ProfesionalDAO dao) {
        super("Modificar Profesional", dao);
        isNew = false;

        nombreField.setText(p.getNombre());
        porcentajeField.setText(String.valueOf(p.getPorcentajeCobro()));
        salarioField.setText(String.valueOf(p.getSalarioBasico()));

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
        return new Profesional(
                nombreField.getText(),
                Float.parseFloat(porcentajeField.getText()),
                Integer.parseInt(salarioField.getText())
        );
    }
}
