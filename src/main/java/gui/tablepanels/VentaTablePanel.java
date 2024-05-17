package gui.tablepanels;

import entity.Venta;
import gui.forms.VentaForm;
import gui.tablemodels.VentaTableModel;
import persistence.dao.VentaDAO;

public class VentaTablePanel extends EntityTablePanel<Venta> {

    public VentaTablePanel(VentaDAO dao) {
        super("Ventas", new VentaTableModel(dao));

        addButton.addActionListener(_ -> openForm(new VentaForm(dao)));
        modifyButton.setVisible(false);
        /*
        modifyButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            VentaTableModel model = ((VentaTableModel) table.getModel());
            openForm(new VentaForm(model.getEntityAtRow(selectedRow), dao));
        });
        */
    }
}