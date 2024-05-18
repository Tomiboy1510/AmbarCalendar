package gui.tablepanels;

import entity.Profesional;
import gui.forms.ProfesionalForm;
import gui.tablemodels.ProfesionalTableModel;
import persistence.dao.ProfesionalDAO;

public class ProfesionalTablePanel extends EntityTablePanel<Profesional> {

    public ProfesionalTablePanel(ProfesionalDAO dao) {
        super("Profesionales", new ProfesionalTableModel(dao));

        addButton.addActionListener(_ -> new ProfesionalForm(dao));

        removeButton.setVisible(false);

        modifyButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            ProfesionalTableModel model = ((ProfesionalTableModel) table.getModel());
            new ProfesionalForm(model.getEntityAtRow(selectedRow), dao);
        });
    }
}
