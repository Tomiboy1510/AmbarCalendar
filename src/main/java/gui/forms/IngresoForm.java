package gui.forms;

import entity.enums.TipoPago;
import gui.formattedfields.IntegerField;
import persistence.dao.HibernateDAO;

import javax.swing.*;

@SuppressWarnings("rawtypes")
public abstract class IngresoForm extends EntityForm {

    protected final JComboBox<TipoPago> tipoPagoField = new JComboBox<>();
    private final IntegerField montoField = new IntegerField(20);

    public IngresoForm(String title, HibernateDAO dao) {
        super(title, dao);
    }

    protected void init() {
        addField("Tipo de Pago", tipoPagoField);
        addField("Monto total", montoField);

        afterInit();
    }
}
