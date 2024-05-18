package gui.tablepanels;

import entity.Venta;
import gui.forms.VentaForm;
import gui.tablemodels.VentaTableModel;
import persistence.dao.VentaDAO;

public class VentaTablePanel extends EntityTablePanel<Venta> {

    public VentaTablePanel(VentaDAO dao) {
        super("Ventas", new VentaTableModel(dao));

        addButton.addActionListener(_ -> new VentaForm(dao));

        modifyButton.setVisible(false);
    }
}