package gui.tablepanels;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class IngresoTablePanel extends EntityTablePanel {

    public IngresoTablePanel() {
        super("Ingresos", new DefaultTableModel());
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
