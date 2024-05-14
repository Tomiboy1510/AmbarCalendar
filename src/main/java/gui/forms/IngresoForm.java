package gui.forms;

import persistence.dao.HibernateDAO;

import javax.swing.*;

@SuppressWarnings("rawtypes")
public abstract class IngresoForm extends EntityForm {

    protected final JTextField tipoPagoField = new JTextField(20);
    protected final JTextField fechaHoraField = new JTextField(20);
    private final JTextField montoField = new JTextField(20);

    public IngresoForm(String title, HibernateDAO dao) {
        super(title, dao);
    }

    protected void init() {
        addField("Tipo de Pago", tipoPagoField);
        addField("Fecha y Hora", fechaHoraField);
        addField("Monto total", montoField);

        afterInit();
    }
}
