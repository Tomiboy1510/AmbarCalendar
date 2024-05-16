package gui.tablepanels;

import entity.Turno;
import gui.forms.TurnoForm;
import gui.tablemodels.TurnoTableModel;
import persistence.dao.ClienteDAO;
import persistence.dao.ProfesionalDAO;
import persistence.dao.TurnoDAO;

import java.util.Date;

public class TurnoTablePanel extends EntityTablePanel<Turno> {

    public TurnoTablePanel(TurnoDAO turnoDAO, ClienteDAO clienteDAO, ProfesionalDAO profesionalDAO) {
        super("Turnos", new TurnoTableModel(turnoDAO));

        addButton.addActionListener(_ -> {
            openForm(new TurnoForm(turnoDAO, clienteDAO, profesionalDAO, new Date()));
        });

        modifyButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            TurnoTableModel model = ((TurnoTableModel) table.getModel());
            openForm(new TurnoForm(model.getEntityAtRow(selectedRow), turnoDAO, clienteDAO, profesionalDAO));
        });
    }
}