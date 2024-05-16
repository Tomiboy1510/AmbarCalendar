package gui.tablepanels;

import entity.Egreso;
import gui.forms.EgresoForm;
import gui.tablemodels.EgresoTableModel;
import persistence.dao.EgresoDAO;

public class EgresoTablePanel extends EntityTablePanel<Egreso> {

    public EgresoTablePanel(EgresoDAO dao) {
        super("Egresos", new EgresoTableModel(dao));

        addButton.addActionListener(_ -> openForm(new EgresoForm(dao)));

        modifyButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            EgresoTableModel model = ((EgresoTableModel) table.getModel());
            openForm(new EgresoForm(model.getEntityAtRow(selectedRow), dao));
        });
    }
}
