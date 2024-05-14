package gui.tablepanels;

import entity.Egreso;
import gui.forms.EgresoForm;
import gui.forms.EntityForm;
import gui.tablemodels.EgresoTableModel;
import persistence.dao.EgresoDAO;

public class EgresoTablePanel extends EntityTablePanel<Egreso> {

    public EgresoTablePanel(EgresoDAO dao) {
        super("Egresos", new EgresoTableModel(dao));

        addButton.addActionListener(_ -> {
            EntityForm form = new EgresoForm(dao);
            form.setVisible(true);
        });

        modifyButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            EgresoTableModel model = ((EgresoTableModel) table.getModel());
            EntityForm form = new EgresoForm(model.getEntityAtRow(selectedRow), dao);
            form.setVisible(true);
        });

        removeButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            EgresoTableModel model = ((EgresoTableModel) table.getModel());
            model.delete(selectedRow);
        });
    }
}
