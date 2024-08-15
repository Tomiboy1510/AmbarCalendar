package gui.tablepanels;

import entity.Profesional;
import gui.MyTableCellRenderer;
import gui.forms.ProfesionalForm;
import gui.tablemodels.ProfesionalTableModel;
import persistence.dao.ProfesionalDAO;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * GUI component used for displaying professionals ({@link Profesional}) and allowing the user to
 * create new ones and modify or delete existing ones.
 */
public class ProfesionalTablePanel extends StandaloneEntityTablePanel<Profesional> {

    public ProfesionalTablePanel(ProfesionalDAO dao) {
        super("Profesionales", new ProfesionalTableModel(dao));

        // Show form for new professional
        addButton.addActionListener(_ -> new ProfesionalForm(dao));

        // Show form for modifying
        modifyButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            ProfesionalTableModel model = ((ProfesionalTableModel) table.getModel());
            new ProfesionalForm(model.getEntityAtRow(selectedRow), dao);
        });

        // Rather than removing professionals, you "invalidate" them
        removeButton.setText("Invalidar");

        // Remove current ActionListener which allows deleting the selected professional
        Arrays.stream(removeButton.getActionListeners())
                .forEach(removeButton::removeActionListener);

        // New ActionListener which just sets their base salary and commission rate to zero
        removeButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            String[] options = {"Sí", "No"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "¿Invalidar? (El porcentaje de cobro y el salario pasan a ser cero)",
                    "Invalidar Profesional",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);

            if (choice == JOptionPane.YES_OPTION) {
                ProfesionalTableModel model = ((ProfesionalTableModel) table.getModel());
                Profesional p = model.getEntityAtRow(selectedRow);
                p.setSalarioBasicoSemanal(0);
                p.setPorcentajeCobro(0f);
                dao.update(p);
            }
        });

        // Set custom default cell renderer
        table.setDefaultRenderer(Object.class, new MyTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column
            ) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Show text in red if the professional has been "invalidated"
                ProfesionalTableModel tableModel = ((ProfesionalTableModel) table.getModel());
                Profesional p = tableModel.getEntityAtRow(row);
                if ((! isSelected) && (p.getSalarioBasicoSemanal() == 0 && p.getPorcentajeCobro() == 0))
                    setForeground(Color.RED);

                return this;
            }
        });
    }
}
