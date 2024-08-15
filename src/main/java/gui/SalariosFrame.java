package gui;

import entity.Turno;
import gui.tablemodels.SalariosTableModel;
import gui.tablepanels.SalariosTablePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * {@link JFrame} to show salaries calculated for a certain period.
 */
public class SalariosFrame extends MyJFrame {

    /**
     * Date format used for formatting dates
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public SalariosFrame(Date startDate, Date endDate, List<Turno> turnos) {
        setTitle("Pago de Salarios");

        // Close if focus is lost
        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                dispose();
            }
        });

        getContentPane().add(new SalariosTablePanel(new SalariosTableModel(turnos)));

        pack();
        setMinimumSize(new Dimension(700, 600));
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
