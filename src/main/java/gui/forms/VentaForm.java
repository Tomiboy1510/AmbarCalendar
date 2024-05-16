package gui.forms;

import entity.Venta;
import entity.enums.TipoPago;
import gui.formattedfields.FechaField;
import persistence.dao.VentaDAO;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VentaForm extends IngresoForm {

    private final FechaField fechaField = new FechaField(20);
    private final JTextField itemsField = new JTextField(20);

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public VentaForm(VentaDAO dao) {
        super("Registrar Venta", dao);
        init();

        fechaField.setText(dateFormat.format(new Date()));
    }

    public VentaForm(Venta v, VentaDAO dao) {
        super("Modificar Venta", dao);
        isNew = false;

        tipoPagoField.setSelectedItem(v.getTipoPago());
        montoField.setText(String.valueOf(v.getMonto()));
        fechaField.setText(dateFormat.format(v.getFechaHora()));
        itemsField.setText(v.getItems().toString());

        init();
    }

    protected void init() {
        super.init();
        addField("Fecha", fechaField);
        addField("Items", itemsField);
        afterInit();
    }

    @Override
    protected Venta buildEntity() {
        Venta v = new Venta();
        v.setTipoPago((TipoPago) tipoPagoField.getSelectedItem());
        v.setMonto(999);
        try {
            v.setFechaHora(dateFormat.parse(fechaField.getText()));
        } catch (Exception e) {
            v.setFechaHora(null);
        }
        v.setItems(null);
        return v;
    }
}
