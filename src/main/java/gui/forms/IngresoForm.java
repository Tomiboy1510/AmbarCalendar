package gui.forms;

import entity.Ingreso;
import entity.enums.TipoPago;
import persistence.dao.EntityDAO;

import javax.swing.*;
import java.util.Arrays;

@SuppressWarnings("rawtypes")
public abstract class IngresoForm extends EntityForm {

    protected final JComboBox<TipoPago> tipoPagoField = new JComboBox<>();

    public IngresoForm(String title, EntityDAO dao) {
        super(title, dao);
    }

    public IngresoForm(String title, Ingreso i, EntityDAO dao) {
        super(title, dao, i.getId());

        tipoPagoField.setSelectedItem(i.getTipoPago());
    }

    protected void init() {
        addField("Tipo de Pago", tipoPagoField);

        Arrays.stream(TipoPago.values()).forEach(tipoPagoField::addItem);
    }
}
