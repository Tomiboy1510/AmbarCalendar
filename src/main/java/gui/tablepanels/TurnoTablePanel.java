package gui.tablepanels;

import entity.Turno;
import gui.MultiLineTableCellRenderer;
import gui.forms.TurnoForm;
import gui.tablemodels.TurnoTableModel;
import persistence.dao.ClienteDAO;
import persistence.dao.ProfesionalDAO;
import persistence.dao.TurnoDAO;

import javax.swing.table.TableColumnModel;
import java.util.Date;

public class TurnoTablePanel extends EntityTablePanel<Turno> {

    public TurnoTablePanel(TurnoDAO turnoDAO, ClienteDAO clienteDAO, ProfesionalDAO profesionalDAO) {
        super("Turnos", new TurnoTableModel(turnoDAO));

        addButton.addActionListener(_ -> new TurnoForm(turnoDAO, clienteDAO, profesionalDAO, new Date()));

        modifyButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            TurnoTableModel model = ((TurnoTableModel) table.getModel());
            new TurnoForm(model.getEntityAtRow(selectedRow), turnoDAO, clienteDAO, profesionalDAO);
        });

        TurnoTableModel tableModel = (TurnoTableModel) table.getModel();
        TableColumnModel columnModel = table.getTableHeader().getColumnModel();
        columnModel.getColumn(tableModel.getColumnIndex("Cliente")).setCellRenderer(new MultiLineTableCellRenderer());
    }
}