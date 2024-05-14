package gui.tabs;

import gui.tablepanels.ClienteTablePanel;
import gui.tablepanels.ProfesionalTablePanel;
import persistence.dao.ClienteDAO;
import persistence.dao.ProfesionalDAO;

import javax.swing.*;
import java.awt.*;

public class PersonasTab extends JPanel {

    public PersonasTab(ClienteDAO clienteDAO, ProfesionalDAO profesionalDAO) {
        super();

        setLayout(new BorderLayout());
        JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new ClienteTablePanel(clienteDAO),
                new ProfesionalTablePanel(profesionalDAO)
        );
        panel.setDividerLocation(0.5);
        panel.setResizeWeight(0.5);

        add(panel, BorderLayout.CENTER);
    }
}
