package gui.forms;

import entity.Turno;
import entity.enums.Servicio;
import gui.formattedfields.FechaField;
import gui.formattedfields.HoraField;
import persistence.dao.TurnoDAO;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TurnoForm extends IngresoForm {

    private final FechaField fechaField = new FechaField(10);
    private final HoraField horaField = new HoraField(10);
    private final JComboBox<Servicio> servicioField = new JComboBox<>();
    private final JComboBox<String> profesionalField = new JComboBox<>();
    private final JComboBox<String> clienteField = new JComboBox<>();
    private final JTextField notasField = new JTextField(20);

    public TurnoForm(TurnoDAO dao) {
        super("Registrar Turno", dao);
        init();

        fechaField.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        horaField.setText(new SimpleDateFormat("hh:mm").format(new Date()));
    }

    public TurnoForm(Turno v, TurnoDAO dao) {
        super("Modificar Turno", dao);
        isNew = false;
        init();
    }

    protected void init() {
        JPanel fechaHoraPanel = new JPanel();
        fechaHoraPanel.add(fechaField);
        fechaHoraPanel.add(horaField);

        addField("Fecha y Hora", fechaHoraPanel);
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
