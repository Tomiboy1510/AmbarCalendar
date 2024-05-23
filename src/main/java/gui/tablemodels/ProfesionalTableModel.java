package gui.tablemodels;

import entity.Profesional;
import persistence.dao.ProfesionalDAO;

public class ProfesionalTableModel extends StandaloneEntityTableModel<Profesional> {

    public ProfesionalTableModel(ProfesionalDAO dao) {
        super(dao, new String[] {"Nombre", "Porcentaje de Cobro", "Salario Básico"}, 40);
    }

    @Override
    public void sortByColumn(int columnIndex) {
        if (sortingColumn == columnIndex)
            sortAscending = (! sortAscending);
        ProfesionalDAO dao = ((ProfesionalDAO) this.dao);
        switch (columnIndex) {
            case 0 -> dao.sortByNombre(sortAscending);
            case 1 -> dao.sortByPorcentajeCobro(sortAscending);
            case 2 -> dao.sortBySalarioBasico(sortAscending);
            default -> {return;}
        }
        sortingColumn = columnIndex;
        refresh();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Profesional p = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getNombre();
            case 1 -> p.getPorcentajeCobro() + "%";
            case 2 -> "$" + p.getSalarioBasico();
            default -> null;
        };
    }
}
