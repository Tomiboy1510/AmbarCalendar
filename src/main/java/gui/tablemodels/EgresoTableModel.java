package gui.tablemodels;

import entity.Egreso;
import persistence.dao.HibernateDAO;

public class EgresoTableModel extends EntityTableModel<Egreso> {

    public EgresoTableModel(HibernateDAO<Egreso> dao) {
        super(dao, Egreso.getFieldNamesButPretty(), Egreso.getFieldNames(), 200);
    }
}
