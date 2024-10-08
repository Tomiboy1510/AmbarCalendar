package gui.tabs;

import gui.forms.InformeForm;
import gui.forms.SalariosForm;
import gui.tablepanels.EgresoTablePanel;
import gui.tablepanels.TurnoTablePanel;
import gui.tablepanels.VentaTablePanel;
import persistence.dao.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * One of the tabs in the main window of the application.
 * Contains everything related to incomes and expenses.
 */
public class FinanzasTab extends JPanel {

    public FinanzasTab(TurnoDAO turnoDAO, ClienteDAO clienteDAO, ProfesionalDAO profesionalDAO,
            VentaDAO ventaDAO, ProductoDAO productoDAO, EgresoDAO egresoDAO
    ) {
        super();

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBorder(new EmptyBorder(10, 10, 0, 10));

        JButton pagosButton = new JButton("Calcular pago de salarios");
        JButton informeButton = new JButton("Informe de finanzas");
        pagosButton.setFocusable(false);
        informeButton.setFocusable(false);

        // Generate financial report
        informeButton.addActionListener(_ -> new InformeForm(turnoDAO, ventaDAO, egresoDAO));

        // Calculate salaries
        pagosButton.addActionListener(_ -> new SalariosForm(turnoDAO, egresoDAO));

        topPanel.add(pagosButton);
        topPanel.add(informeButton);

        JSplitPane nestedPanel = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                new TurnoTablePanel(turnoDAO, clienteDAO, profesionalDAO),
                new VentaTablePanel(ventaDAO, productoDAO)
        );

        JSplitPane centerPanel = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new EgresoTablePanel(egresoDAO),
                nestedPanel
        );

        nestedPanel.setResizeWeight(0.5);
        centerPanel.setResizeWeight(0.25);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        SwingUtilities.invokeLater(() -> {
            nestedPanel.setDividerLocation(0.5);
            centerPanel.setDividerLocation(0.25);
        });
    }
}
