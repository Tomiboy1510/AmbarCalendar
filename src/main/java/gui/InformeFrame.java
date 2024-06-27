package gui;

import entity.Egreso;
import entity.Turno;
import entity.Venta;
import entity.enums.Servicio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * {@link JFrame} to show results of financial report.
 */
public class InformeFrame extends MyJFrame {

    /**
     * Date format used for formatting dates
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public InformeFrame(Date startDate, Date endDate, java.util.List<Egreso> egresos, java.util.List<Venta> ventas, List<Turno> turnos) {
        setTitle("Informe de Finanzas");

        // Close if focus is lost
        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                dispose();
            }
        });

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        textArea.setLineWrap(true);
        textArea.setText(buildText(startDate, endDate, egresos, ventas, turnos));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(null);
        // Move scrollbar to the top
        SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(0));
        getContentPane().add(scrollPane);
        setMinimumSize(new Dimension(600, 600));
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static String buildText(
            Date startDate,
            Date endDate,
            List<Egreso> egresos,
            List<Venta> ventas,
            List<Turno> turnos
    ) {
        int montoEgresos = 0;
        int montoSalarios = 0;
        int montoTurnos = 0;
        int montoVentas;
        int[] ingresosPorServicio = new int[Servicio.values().length];
        Arrays.fill(ingresosPorServicio, 0);

        // Sum expenses and salary
        for (Egreso e : egresos) {
            montoEgresos += e.getMonto();
            if (e.getMotivo().toLowerCase().contains("salario"))
                montoSalarios += e.getMonto();
        }

        // Sum income from product sales
        montoVentas = ventas.stream()
                .mapToInt(Venta::getMonto)
                .sum();

        // Sum income from service sales (and distinguish between service types)
        for (Turno t : turnos) {
            montoTurnos += t.getMontoPagado();
            ingresosPorServicio[t.getServicio().ordinal()] += t.getMontoPagado();
        }

        // Total income
        int montoIngresos = montoTurnos + montoVentas;

        StringBuilder builder = new StringBuilder();

        builder.append("Informe de Finanzas - Desde el ");
        builder.append(dateFormat.format(startDate));
        builder.append(" hasta el ");
        builder.append(dateFormat.format(endDate));
        builder.append("\n\n");
        builder.append("Ingresos:\n\n");
        builder.append("    - Ingresos totales: $");
        builder.append(montoIngresos);
        builder.append("\n    - Ventas de productos: $");
        builder.append(montoVentas);

        if  (montoIngresos > 0) {
            builder.append(" (");
            builder.append(String.format("%.2f", (float) montoVentas * 100 / montoIngresos));
            builder.append("%)");
        }

        builder.append("\n    - Servicios: $");
        builder.append(montoTurnos);

        if  (montoIngresos > 0) {
            builder.append(" (");
            builder.append(String.format("%.2f", (float) montoTurnos * 100 / montoIngresos));
            builder.append("%)");
        }

        for (int i = 0; i < ingresosPorServicio.length; i++) {
            builder.append("\n        . ");
            builder.append(Servicio.values()[i]);
            builder.append(": $");
            builder.append(ingresosPorServicio[i]);
        }

        builder.append("\n\nEgresos:\n\n");
        builder.append("    - Egresos totales: $");
        builder.append(montoEgresos);
        builder.append("\n    - Salarios: $");
        builder.append(montoSalarios);

        if  (montoEgresos > 0) {
            builder.append(" (");
            builder.append(String.format("%.2f", (float) montoSalarios * 100 / montoEgresos));
            builder.append("%)");
        }

        if (montoIngresos - montoEgresos >= 0)
            builder.append("\n\nGanancia: $");
        else
            builder.append("\n\nPérdida: $");

        builder.append(Math.abs(montoIngresos - montoEgresos));

        if (montoIngresos > 0) {
            builder.append(" (");
            builder.append(String.format("%.2f", Math.abs((float) (montoIngresos - montoEgresos) * 100 / montoIngresos)));
            builder.append("%)");
        }

        return builder.toString();
    }
}
