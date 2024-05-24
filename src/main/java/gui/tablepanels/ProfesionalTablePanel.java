package gui.tablepanels;

import entity.Profesional;
import gui.MyTableCellRenderer;
import gui.forms.ProfesionalForm;
import gui.tablemodels.ProfesionalTableModel;
import persistence.dao.ProfesionalDAO;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ProfesionalTablePanel extends StandaloneEntityTablePanel<Profesional> {

    public ProfesionalTablePanel(ProfesionalDAO dao) {
        super("Profesionales", new ProfesionalTableModel(dao));

        addButton.addActionListener(_ -> new ProfesionalForm(dao));

        modifyButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            ProfesionalTableModel model = ((ProfesionalTableModel) table.getModel());
            new ProfesionalForm(model.getEntityAtRow(selectedRow), dao);
        });

        removeButton.setText("Invalidar");

        // Remove current ActionListener which allows deleting the selected Profesional
        Arrays.stream(removeButton.getActionListeners())
                .forEach(removeButton::removeActionListener);

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
                p.setSalarioBasico(0);
                p.setPorcentajeCobro(0f);
                dao.update(p);
            }
        });

        table.setDefaultRenderer(Object.class, new MyTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column
            ) {
                Component res = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ProfesionalTableModel tableModel = ((ProfesionalTableModel) table.getModel());
                Profesional p = tableModel.getEntityAtRow(row);
                if ((! isSelected) && (p.getSalarioBasico() == 0 && p.getPorcentajeCobro() == 0))
                    setForeground(Color.RED);
                return res;
            }
        });
    }
}
