package gui.forms;

import entity.Egreso;
import gui.formattedfields.FechaField;
import gui.formattedfields.IntegerField;
import persistence.dao.EgresoDAO;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Form for creating or modifying expenses ({@link Egreso}).
 */
public class EgresoForm extends StandaloneEntityForm {

    private final JTextField motivoField = new JTextField(20);
    private final IntegerField montoField = new IntegerField(20);
    private final FechaField fechaField = new FechaField(20);

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public EgresoForm(EgresoDAO dao) {
        super("Registrar Egreso", dao);
        init();

        fechaField.setText(dateFormat.format(new Date()));
    }

    private void init() {
        addField("Motivo", motivoField);
        addField("Monto", montoField);
        addField("Fecha", fechaField);

        afterInit();
    }

    @Override
    protected Egreso buildEntity() {
        Egreso e = new Egreso();
        e.setId(id);
        e.setMotivo(motivoField.getText());
        try {
            e.setMonto(Integer.parseInt(montoField.getText()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Monto obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        try {
            e.setFecha(dateFormat.parse(fechaField.getText()));
        } catch (ParseException ex) {
            e.setFecha(null);
        }

        return e;
    }
}
