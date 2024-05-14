package gui.forms;

import entity.Turno;
import persistence.dao.TurnoDAO;

import javax.swing.*;

public class TurnoForm extends IngresoForm {

    private final JTextField servicioField = new JTextField(20);
    private final JTextField profesionalField = new JTextField(20);
    private final JTextField clienteField = new JTextField(20);
    private final JTextField notasField = new JTextField(20);

    public TurnoForm(TurnoDAO dao) {
        super("Registrar Turno", dao);
        init();
    }

    public TurnoForm(Turno v, TurnoDAO dao) {
        super("Modificar Turno", dao);
        isNew = false;
        init();
    }

    protected void init() {
        addField("Servicio", servicioField);
        addField("Profesional a cargo", profesionalField);
        addField("Cliente", clienteField);
        addField("Notas adicionales", notasField);
        super.init();
    }

    @Override
    protected Turno buildEntity() {
        return null;
    }
}
