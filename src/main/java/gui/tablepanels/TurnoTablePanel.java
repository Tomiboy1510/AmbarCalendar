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

/**
 * GUI component used for displaying service sales ({@link Turno}) and allowing the user to
 * create new ones and modify or delete existing ones.
 */
public class TurnoTablePanel extends StandaloneEntityTablePanel<Turno> {

    public TurnoTablePanel(TurnoDAO turnoDAO, ClienteDAO clienteDAO, ProfesionalDAO profesionalDAO) {
        super("Turnos", new TurnoTableModel(turnoDAO));

        // Show form for new service sale
        addButton.addActionListener(_ -> new TurnoForm(turnoDAO, clienteDAO, profesionalDAO, new Date()));

        // Show form for modifying
        modifyButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            TurnoTableModel model = ((TurnoTableModel) table.getModel());
            new TurnoForm(model.getEntityAtRow(selectedRow), turnoDAO, clienteDAO, profesionalDAO);
        });

        // Get current date and time
        final Date today = new Date();

        TableColumnModel columnModel = table.getTableHeader().getColumnModel();
        TurnoTableModel tableModel = ((TurnoTableModel) table.getModel());

        // Set custom cell renderer only for one column ("Monto Pagado")
        columnModel.getColumn(tableModel.getColumnIndex("Monto Pagado")).setCellRenderer(new MyTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column
            ) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Show text in red if the service was provided but hasn't been fully paid yet
                Turno t = tableModel.getEntityAtRow(row);
                if ((! isSelected) &&
                        t.getMontoPagado() < t.getMonto() &&
                        t.getFechaHora().before(today)
                )
                    setForeground(Color.RED);

                return this;
            }
        });

        // Set multiline cell renderer for the "Cliente" column
        columnModel.getColumn(tableModel.getColumnIndex("Cliente")).setCellRenderer(new MultiLineTableCellRenderer());
    }
}