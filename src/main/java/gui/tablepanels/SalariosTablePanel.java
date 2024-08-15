package gui.tablepanels;

import entity.Profesional;
import gui.tablemodels.SalariosTableModel;

/**
 * GUI component used for displaying professionals ({@link Profesional}) and the services they provided
 * in a specific frame of time, and allowing the user to pay salaries.
 */
public class SalariosTablePanel extends MyTablePanel {

    public SalariosTablePanel(SalariosTableModel tableModel) {
        super(tableModel, false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowSelectionAllowed(false);
    }
}
