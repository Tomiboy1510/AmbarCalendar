package gui.forms;

import gui.InformeFrame;
import gui.formattedfields.FechaField;
import persistence.dao.*;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Form for generating a financial report
 */
public class InformeForm extends MyForm {

    private final FechaField desdeField = new FechaField(20);
    private final FechaField hastaField = new FechaField(20);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

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

        Date today = new Date();
        hastaField.setText(dateFormat.format(today));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.MONTH, -1);
        desdeField.setText(dateFormat.format(calendar.getTime()));

        saveButton.setText("Generar Informe");
        saveButton.addActionListener(_ -> {
            setHasFocusOwnership(false);

            Date startDate;
            Date endDate;
            try {
                startDate = dateTimeFormat.parse(desdeField.getText() + " 00:00");
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Fecha de inicio inválida", "Error", JOptionPane.ERROR_MESSAGE);
                setHasFocusOwnership(true);
                return;
            }
            try {
                endDate = dateTimeFormat.parse(hastaField.getText() + " 23:59");
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Fecha de fin inválida", "Error", JOptionPane.ERROR_MESSAGE);
                setHasFocusOwnership(true);
                return;
            }

            setHasFocusOwnership(true);
            new InformeFrame(
                    startDate,
                    endDate,
                    egresoDAO.getAllWithDateRange(startDate, endDate),
                    ventaDAO.getAllWithDateRange(startDate, endDate),
                    turnoDAO.getAllWithDateRange(startDate, endDate)
            );
            dispose();
        });

        afterInit();
    }
}
