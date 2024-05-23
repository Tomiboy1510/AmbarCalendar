package gui.forms;

import entity.Profesional;
import gui.formattedfields.IntegerField;
import gui.formattedfields.FloatField;
import persistence.dao.ProfesionalDAO;

import javax.swing.*;

public class ProfesionalForm extends StandaloneEntityForm {

    private final JTextField nombreField = new JTextField(20);
    private final FloatField porcentajeField = new FloatField(20);
    private final IntegerField salarioField = new IntegerField(20);

    public ProfesionalForm(ProfesionalDAO dao) {
        super("Añadir Profesional", dao);
        init();
    }

    public ProfesionalForm(Profesional p, ProfesionalDAO dao) {
        super("Modificar Profesional", dao, p.getId());
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
        Profesional p = new Profesional();
        p.setId(id);
        p.setNombre(nombreField.getText());
        try {
            p.setPorcentajeCobro(Float.parseFloat(porcentajeField.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Porcentaje de cobro obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        try {
            p.setSalarioBasico(Integer.parseInt(salarioField.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Salario básico obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return p;
    }
}
