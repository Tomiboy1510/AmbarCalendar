package gui.tabs;

import gui.tablepanels.ClienteTablePanel;
import gui.tablepanels.ProfesionalTablePanel;

import javax.swing.*;
import java.awt.*;

public class PersonasPanel extends JPanel {

    public PersonasPanel() {
        super();

        setLayout(new BorderLayout());
        JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new ClienteTablePanel(),
                new ProfesionalTablePanel()
        );
        panel.setDividerLocation(0.5);
        panel.setResizeWeight(0.5);

        add(panel, BorderLayout.CENTER);
    }
}
