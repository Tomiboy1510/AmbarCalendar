package gui.tablemodels;

import entity.Turno;
import persistence.dao.HibernateDAO;

public class TurnoTableModel extends EntityTableModel<Turno> {

    public TurnoTableModel(HibernateDAO<Turno> dao) {
        super(dao, Turno.getFieldNamesButPretty(), Turno.getFieldNames(), 200);
    }
}
