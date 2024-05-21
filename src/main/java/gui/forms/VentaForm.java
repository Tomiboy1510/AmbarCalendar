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

public class VentaForm extends IngresoForm {

    private final FechaField fechaField = new FechaField(20);
    private final ItemVentaTablePanel itemsField;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public VentaForm(VentaDAO ventaDAO, ProductoDAO productoDAO) {
        super("Registrar Venta", ventaDAO);
        dateFormat.setLenient(false);
        itemsField = new ItemVentaTablePanel(new ItemVentaTableModel(), productoDAO);

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
