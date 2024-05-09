package gui.tabs;

import gui.tablepanels.EntityTablePanel;
import gui.tablepanels.ProductoTablePanel;

import javax.swing.*;
import java.awt.*;

public class InventarioPanel extends JPanel {

    public InventarioPanel() {
        super();
        setLayout(new BorderLayout());
        EntityTablePanel tablePanel = new ProductoTablePanel();
        add(tablePanel, BorderLayout.CENTER);

    }
}
