package gui;

import entity.Egreso;
import entity.Profesional;
import entity.Turno;
import persistence.dao.EgresoDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * {@link JFrame} to show salaries calculated for a certain period.
 */
public class SalariosFrame extends MyJFrame {

    /**
     * Date format used for formatting dates
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public SalariosFrame(Date startDate, Date endDate, List<Turno> turnos, EgresoDAO egresoDAO) {
        // Compute number of weeks elapsed
        long millisElapsed = Math.abs(endDate.getTime() - startDate.getTime());
        long daysElapsed = TimeUnit.DAYS.convert(millisElapsed, TimeUnit.MILLISECONDS);
        long weeksElapsed = Math.round(daysElapsed / 7f);

        setTitle("Pago de Salarios (" + dateFormat.format(startDate) + "  -  "
                + dateFormat.format(endDate) + ") (" + weeksElapsed + " semana"
                + (weeksElapsed == 1 ? "" : "s") + ")");

        // Close if focus is lost
        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                dispose();
            }
        });

        getContentPane().setLayout(new BorderLayout());

        // Divide frame into header and content panels
        JPanel headerPanel = new JPanel(new GridLayout(1, 4));
        JScrollPane scrollPane = new JScrollPane();
        JPanel outerPanel = new JPanel(new BorderLayout());
        JPanel innerPanel = new JPanel(new GridLayout(0, 4));
        outerPanel.add(innerPanel, BorderLayout.NORTH);

        // Headers
        JLabel label = new JLabel("Profesional");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(new EmptyBorder(5,10,5,10));
        headerPanel.add(label);

        label = new JLabel("Servicios realizados");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(new EmptyBorder(5,10,5,10));
        headerPanel.add(label);

        label = new JLabel("Salario correspondiente");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(new EmptyBorder(5,10,5,10));
        headerPanel.add(label);

        headerPanel.add(new JLabel(""));

        // Rows
        HashMap<Integer, SalariosInfo> map = new HashMap<>();
        for (Turno t : turnos) {
            // Check validity
            if (! t.getProfesional().isValid())
                continue;

            if (! map.containsKey(t.getProfesional().getId())) {
                map.put(
                        t.getProfesional().getId(),
                        new SalariosInfo(
                            t.getProfesional(),
                            0,
                            (int) (t.getProfesional().getSalarioBasicoSemanal() * weeksElapsed)
                        )
                );
            }

            SalariosInfo salariosInfo = map.get(t.getProfesional().getId());
            salariosInfo.serviciosProvistos ++;

            salariosInfo.salario += (int) (t.getMonto() * t.getProfesional().getPorcentajeCobro() / 100);
        }

        map.forEach((_, salariosInfo) -> {
            innerPanel.add(new JLabel(salariosInfo.profesional.getNombre()));
            innerPanel.add(new JLabel(String.valueOf(salariosInfo.serviciosProvistos)));
            innerPanel.add(new JLabel("$" + salariosInfo.salario));
            JButton button = new JButton("Registrar pago");
            button.setFocusable(false);
            button.addActionListener(_ -> {
                button.setEnabled(false);
                Egreso egreso = new Egreso();
                egreso.setMonto(salariosInfo.salario);
                egreso.setFecha(new Date());
                egreso.setMotivo("Salario de " + salariosInfo.profesional.getNombre() +
                        " (" + dateFormat.format(startDate) + " - " + dateFormat.format(endDate) + ")");
                egresoDAO.save(egreso);
            });
            innerPanel.add(button);
        });

        scrollPane.setViewportView(outerPanel);
        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // To keep headers and content columns aligned
        setMinimumSize(new Dimension((int) (innerPanel.getMinimumSize().width * 1.2), getMinimumSize().height));

        setSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static class SalariosInfo {
        Profesional profesional;
        int serviciosProvistos;
        int salario;

        public SalariosInfo(Profesional profesional, int serviciosProvistos, int salario) {
            this.profesional = profesional;
            this.serviciosProvistos = serviciosProvistos;
            this.salario = salario;
        }
    }
}
