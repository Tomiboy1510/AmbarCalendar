package gui.tablepanels;

import entity.Venta;
import gui.forms.EntityForm;
import gui.forms.VentaForm;
import gui.tablemodels.VentaTableModel;
import persistence.dao.VentaDAO;

public class VentaTablePanel extends EntityTablePanel<Venta> {

    public VentaTablePanel(VentaDAO dao) {
        super("Ventas", new VentaTableModel(dao));

        addButton.addActionListener(_ -> {
            EntityForm form = new VentaForm(dao);
            form.setVisible(true);
        });

        modifyButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            VentaTableModel model = ((VentaTableModel) table.getModel());
            EntityForm form = new VentaForm(model.getEntityAtRow(selectedRow), dao);
            form.setVisible(true);
        });

        removeButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            VentaTableModel model = ((VentaTableModel) table.getModel());
            model.delete(selectedRow);
        });
    }
}