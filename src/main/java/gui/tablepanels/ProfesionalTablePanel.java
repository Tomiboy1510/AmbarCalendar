package gui.tablepanels;

import entity.Profesional;
import gui.forms.ProfesionalForm;
import gui.tablemodels.ProfesionalTableModel;
import persistence.dao.ProfesionalDAO;

public class ProfesionalTablePanel extends EntityTablePanel<Profesional> {

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
        removeButton.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;

            ProfesionalTableModel model = ((ProfesionalTableModel) table.getModel());
            Profesional p = model.getEntityAtRow(selectedRow);
            p.setSalarioBasico(0);
            p.setPorcentajeCobro(0f);
            dao.update(p);
        });
    }
}
