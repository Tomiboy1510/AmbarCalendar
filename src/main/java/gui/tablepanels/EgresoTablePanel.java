package gui.tablepanels;

import entity.Egreso;
import gui.forms.EgresoForm;
import gui.tablemodels.EgresoTableModel;
import persistence.dao.EgresoDAO;

/**
 * GUI component used for displaying expenses ({@link Egreso}) and allowing the user to
 * create new ones and modify or delete existing ones.
 */
public class EgresoTablePanel extends StandaloneEntityTablePanel<Egreso> {

    public EgresoTablePanel(EgresoDAO dao) {
        super("Egresos", new EgresoTableModel(dao));

        // Show form for new expense
        addButton.addActionListener(_ -> new EgresoForm(dao));

        // Does not allow modifying
        modifyButton.setVisible(false);
    }
}
