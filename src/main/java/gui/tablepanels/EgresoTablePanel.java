package gui.tablepanels;

import entity.Egreso;
import gui.forms.EgresoForm;
import gui.tablemodels.EgresoTableModel;
import persistence.dao.EgresoDAO;

public class EgresoTablePanel extends StandaloneEntityTablePanel<Egreso> {

    public EgresoTablePanel(EgresoDAO dao) {
        super("Egresos", new EgresoTableModel(dao));

        addButton.addActionListener(_ -> new EgresoForm(dao));

        modifyButton.setVisible(false);
    }
}
