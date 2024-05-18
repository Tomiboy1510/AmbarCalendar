package gui.tablemodels;

import entity.Venta;
import persistence.dao.HibernateDAO;

public class VentaTableModel extends EntityTableModel<Venta> {

    public VentaTableModel(HibernateDAO<Venta> dao) {
        super(dao, Venta.getFieldNamesButPretty(), Venta.getFieldNames(), 200);
    }
}
