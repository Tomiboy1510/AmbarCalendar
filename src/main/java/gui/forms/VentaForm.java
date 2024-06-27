package gui.forms;

import entity.Venta;
import entity.enums.TipoPago;
import gui.formattedfields.FechaField;
import gui.tablemodels.ItemVentaTableModel;
import gui.tablepanels.ItemVentaTablePanel;
import persistence.dao.ProductoDAO;
import persistence.dao.VentaDAO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Form for creating or modifying product sales ({@link Venta}).
 */
public class VentaForm extends IngresoForm {

    private final FechaField fechaField = new FechaField(20);

    /**
     * Panel for adding and removing items to the sale
     */
    private final ItemVentaTablePanel itemsField;

    /**
     * Date format used for parsing dates
     */
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public VentaForm(VentaDAO ventaDAO, ProductoDAO productoDAO) {
        super("Registrar Venta", ventaDAO);
        // Do not allow invalid dates (such as 99/12/2001 for example)
        dateFormat.setLenient(false);
        itemsField = new ItemVentaTablePanel(new ItemVentaTableModel(), productoDAO, this);

        init();

        fechaField.setText(dateFormat.format(new Date()));
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
        v.setId(id);
        v.setTipoPago((TipoPago) tipoPagoField.getSelectedItem());
        try {
            v.setFechaHora(dateFormat.parse(fechaField.getText()));
        } catch (Exception e) {
            v.setFechaHora(null);
        }
        v.setItems(itemsField.getItems());

        return v;
    }
}
