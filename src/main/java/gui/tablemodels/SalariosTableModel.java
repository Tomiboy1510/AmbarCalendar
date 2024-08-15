package gui.tablemodels;

import entity.Profesional;
import entity.Turno;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Table model to manage and display in a {@link JTable} professionals ({@link Profesional}),
 * the services they provided in a specific frame of time and the corresponding salaries.
 */
public class SalariosTableModel extends EntityTableModel {

    private final List<Profesional> profesionales;
    private final JButton boton = new JButton("Hola");

    public SalariosTableModel(List<Turno> turnos) {
        super(new String[] {"Profesional", "Servicios realizados", "Monto a pagar", ""});
        profesionales = new ArrayList<>();
    }

    @Override
    public Object getEntityAtRow(int rowIndex) {
        return profesionales.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        //return profesionales.size();
        return 15;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return boton;
    }
}
