package gui.forms;

import entity.Ingreso;
import entity.enums.TipoPago;
import gui.formattedfields.IntegerField;
import persistence.dao.HibernateDAO;

import javax.swing.*;
import java.util.Arrays;

@SuppressWarnings("rawtypes")
public abstract class IngresoForm extends EntityForm {

    protected final JComboBox<TipoPago> tipoPagoField = new JComboBox<>();
    protected final IntegerField montoField = new IntegerField(20);

    public IngresoForm(String title, HibernateDAO dao) {
        super(title, dao);
    }

    public IngresoForm(String title, Ingreso i, HibernateDAO dao) {
        super(title, dao, i.getId());

        tipoPagoField.setSelectedItem(i.getTipoPago());
        montoField.setText(String.valueOf(i.getMonto()));
    }

    protected void init() {
        addField("Tipo de Pago", tipoPagoField);
        addField("Monto total", montoField);

        Arrays.stream(TipoPago.values()).forEach(tipoPagoField::addItem);
    }
}
