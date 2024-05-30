package gui.tablepanels;

import entity.Turno;
import gui.MultiLineTableCellRenderer;
import gui.MyTableCellRenderer;
import gui.forms.TurnoForm;
import gui.tablemodels.TurnoTableModel;
import persistence.dao.ClienteDAO;
import persistence.dao.ProfesionalDAO;
import persistence.dao.TurnoDAO;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.Date;

public class TurnoTablePanel extends StandaloneEntityTablePanel<Turno> {

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

        final Date today = new Date();

        TableColumnModel columnModel = table.getTableHeader().getColumnModel();
        TurnoTableModel tableModel = ((TurnoTableModel) table.getModel());

        columnModel.getColumn(tableModel.getColumnIndex("Monto Pagado")).setCellRenderer(new MyTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column
            ) {
                Component res = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Turno t = tableModel.getEntityAtRow(row);
                if ((! isSelected) &&
                        t.getMontoPagado() < t.getMonto() &&
                        t.getFechaHora().before(today)
                )
                    setForeground(Color.RED);
                return res;
            }
        });

        columnModel.getColumn(tableModel.getColumnIndex("Cliente")).setCellRenderer(new MultiLineTableCellRenderer());
    }
}