package gui.forms;

import entity.Venta;
import gui.formattedfields.FechaField;
import persistence.dao.VentaDAO;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VentaForm extends IngresoForm {

    private final FechaField fechaField = new FechaField(20);
    private final JTextField itemsField = new JTextField(20);

    public VentaForm(VentaDAO dao) {
        super("Registrar Venta", dao);
        init();

        fechaField.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
    }

    public VentaForm(Venta v, VentaDAO dao) {
        super("Modificar Venta", dao);
        isNew = false;
        init();
    }

    protected void init() {
        addField("Fecha", fechaField);
        addField("Items", itemsField);
        super.init();
    }

    @Override
    protected Venta buildEntity() {
        return null;
    }
}
