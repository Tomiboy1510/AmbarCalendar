package gui.tablepanels;

import entity.Profesional;
import gui.forms.EntityForm;
import gui.forms.ProfesionalForm;
import gui.tablemodels.ProfesionalTableModel;
import persistence.dao.ProfesionalDAO;

public class ProfesionalTablePanel extends EntityTablePanel<Profesional> {

    public ProfesionalTablePanel(ProfesionalDAO dao) {
        super("Profesionales", new ProfesionalTableModel(dao));

        addButton.addActionListener(_ -> {
            EntityForm form = new ProfesionalForm(dao);
            form.setVisible(true);
        });

        modifyButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            ProfesionalTableModel model = ((ProfesionalTableModel) table.getModel());
            EntityForm form = new ProfesionalForm(model.getEntityAtRow(selectedRow), dao);
            form.setVisible(true);
        });

        removeButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            ProfesionalTableModel model = ((ProfesionalTableModel) table.getModel());
            model.delete(selectedRow);
        });
    }
}
