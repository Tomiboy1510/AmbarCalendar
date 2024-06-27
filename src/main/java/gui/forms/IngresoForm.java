package gui.forms;

import entity.Ingreso;
import entity.enums.TipoPago;
import persistence.dao.EntityDAO;

import javax.swing.*;
import java.util.Arrays;

/**
 * Form for creating or modifying incomes ({@link Ingreso}).
 */
@SuppressWarnings("rawtypes")
public abstract class IngresoForm extends StandaloneEntityForm {

    protected final JComboBox<TipoPago> tipoPagoField = new JComboBox<>();

    public IngresoForm(String title, EntityDAO dao) {
        super(title, dao);
    }

    /**
     * Constructor to create a form for modifying an income
     * @param i the income whose data will be used to fill the form
     * @param dao the DAO used to persist changes to the income
     */
    public IngresoForm(String title, Ingreso i, EntityDAO dao) {
        super(title, dao, i.getId());
        isNew = false;

        tipoPagoField.setSelectedItem(i.getTipoPago());
    }

    protected void init() {
        addField("Tipo de Pago", tipoPagoField);

        Arrays.stream(TipoPago.values()).forEach(tipoPagoField::addItem);
    }
}
