package gui.tablepanels;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class EgresoTablePanel extends EntityTablePanel {

    public EgresoTablePanel() {
        super("Egresos", new DefaultTableModel());
    }

    @Override
    protected ActionListener getAddButtonHandler() {
        return null;
    }

    @Override
    protected ActionListener getModifyButtonHandler() {
        return null;
    }

    @Override
    protected ActionListener getRemoveButtonHandler() {
        return null;
    }
}
