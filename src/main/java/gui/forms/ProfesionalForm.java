package gui.forms;

import entity.Profesional;
import gui.formattedfields.IntegerField;
import gui.formattedfields.FloatField;
import persistence.dao.ProfesionalDAO;

import javax.swing.*;

/**
 * Form for creating or modifying professionals ({@link Profesional}).
 */
public class ProfesionalForm extends StandaloneEntityForm {

    private final JTextField nombreField = new JTextField(20);
    private final FloatField porcentajeField = new FloatField(20);
    private final IntegerField salarioField = new IntegerField(20);

    public ProfesionalForm(ProfesionalDAO dao) {
        super("Añadir Profesional", dao);
        init();
    }

    /**
     * Constructor to create a form for modifying a professional
     * @param p the professional whose data will be used to fill the form
     * @param dao the DAO used to persist changes to the professional
     */
    public ProfesionalForm(Profesional p, ProfesionalDAO dao) {
        super("Modificar Profesional", dao, p.getId());
        isNew = false;

        nombreField.setText(p.getNombre());
        porcentajeField.setText(String.valueOf(p.getPorcentajeCobro()));
        salarioField.setText(String.valueOf(p.getSalarioBasicoSemanal()));

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
            p.setSalarioBasicoSemanal(Integer.parseInt(salarioField.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Salario básico obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return p;
    }
}
