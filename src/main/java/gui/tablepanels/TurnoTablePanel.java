package gui.tablepanels;

import entity.Turno;
import gui.forms.EntityForm;
import gui.forms.TurnoForm;
import gui.tablemodels.TurnoTableModel;
import persistence.dao.TurnoDAO;

public class TurnoTablePanel extends EntityTablePanel<Turno> {

    public TurnoTablePanel(TurnoDAO dao) {
        super("Turnos", new TurnoTableModel(dao));

        addButton.addActionListener(_ -> {
            EntityForm form = new TurnoForm(dao);
            form.setVisible(true);
        });

        modifyButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            TurnoTableModel model = ((TurnoTableModel) table.getModel());
            EntityForm form = new TurnoForm(model.getEntityAtRow(selectedRow), dao);
            form.setVisible(true);
        });

        removeButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            TurnoTableModel model = ((TurnoTableModel) table.getModel());
            model.delete(selectedRow);
        });
    }
}