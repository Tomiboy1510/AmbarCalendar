package gui.tablemodels;

import entity.Profesional;
import persistence.dao.ProfesionalDAO;

public class ProfesionalTableModel extends EntityTableModel<Profesional> {

    public ProfesionalTableModel(ProfesionalDAO dao) {
        super(dao, new String[] {
                "Nombre",
                "Porcentaje de Cobro",
                "Salario Básico"},
        40);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Profesional p = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getNombre();
            case 1 -> p.getPorcentajeCobro();
            case 2 -> p.getSalarioBasico();
            default -> null;
        };
    }
}
