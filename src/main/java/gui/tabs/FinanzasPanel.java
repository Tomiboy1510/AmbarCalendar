package gui.tabs;

import gui.tablepanels.EgresoTablePanel;
import gui.tablepanels.IngresoTablePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FinanzasPanel extends JPanel {

    public FinanzasPanel() {
        super();

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBorder(new EmptyBorder(10, 10, 0, 10));

        JButton pagosButton = new JButton("Registrar pago de salarios");
        JButton informeButton = new JButton("Informe de contabilidad");
        pagosButton.setFocusable(false);
        informeButton.setFocusable(false);

        topPanel.add(pagosButton);
        topPanel.add(informeButton);

        JSplitPane centerPanel = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new EgresoTablePanel(),
                new IngresoTablePanel()
        );
        centerPanel.setDividerLocation(0.5);
        centerPanel.setResizeWeight(0.5);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }
}
