package gui.tablemodels;

import entity.Profesional;
import persistence.dao.ProfesionalDAO;

public class ProfesionalTableModel extends EntityTableModel<Profesional> {

    public ProfesionalTableModel(ProfesionalDAO dao) {
        super(dao, Profesional.getFieldNamesButPretty(), Profesional.getFieldNames(), 40);
    }
}
