package gui.forms;

import entity.Egreso;
import gui.formattedfields.FechaField;
import gui.formattedfields.IntegerField;
import persistence.dao.EgresoDAO;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EgresoForm extends EntityForm {

    private final JTextField motivoField = new JTextField(20);
    private final IntegerField montoField = new IntegerField(20);
    private final FechaField fechaField = new FechaField(20);

    public EgresoForm(EgresoDAO dao) {
        super("Registrar Egreso", dao);
        init();

        fechaField.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
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
        Egreso e = new Egreso();
        e.setMotivo(motivoField.getText());
        e.setMonto(Integer.parseInt(montoField.getText()));
        try {
            e.setFecha(new SimpleDateFormat("dd/MM/yyyy").parse(fechaField.getText()));
        } catch (ParseException ex) {
            e.setFecha(null);
        }
        return e;
    }
}
