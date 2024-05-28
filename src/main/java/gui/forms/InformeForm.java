package gui.forms;

import entity.Egreso;
import entity.Turno;
import entity.Venta;
import gui.formattedfields.FechaField;
import persistence.dao.*;

public class InformeForm extends MyForm {

    private final FechaField desdeField = new FechaField(20);
    private final FechaField hastaField = new FechaField(20);

    private final TurnoDAO turnoDAO;
    private final VentaDAO ventaDAO;
    private final EgresoDAO egresoDAO;

    public InformeForm(TurnoDAO turnoDAO, VentaDAO ventaDAO, EgresoDAO egresoDAO) {
        super("Informe de Finanzas");
        this.turnoDAO = turnoDAO;
        this.ventaDAO = ventaDAO;
        this.egresoDAO = egresoDAO;
        init();
    }

    private void init() {
        addField("Desde", desdeField);
        addField("Hasta", hastaField);

        saveButton.setText("Generar Informe");
        saveButton.addActionListener(_ -> {
            setHasFocusOwnership(false);
            // Validate dates

            setHasFocusOwnership(true);

            // Filter by date
            int ingresos =
                    ventaDAO.getAll().stream().mapToInt(Venta::getMonto).sum() +
                    turnoDAO.getAll().stream().mapToInt(Turno::getMontoPagado).sum();
            int egresos =
                    egresoDAO.getAll().stream().mapToInt(Egreso::getMonto).sum();

            System.out.println("Ingresos: $" + ingresos);
            System.out.println("Egresos: $" + egresos);
            System.out.println("Beneficio: " + ((ingresos - egresos) / (float) ingresos) * 100 + "%");

            dispose();
        });

        afterInit();
    }
}
