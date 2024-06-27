package gui.tabs;

import gui.tablepanels.ProductoTablePanel;
import persistence.dao.ProductoDAO;

import javax.swing.*;
import java.awt.*;

/**
 * One of the tabs in the main window of the application.
 * Contains everything related to inventory.
 */
public class InventarioTab extends JPanel {

    public InventarioTab(ProductoDAO productoDAO) {
        super();
        setLayout(new BorderLayout());

        ProductoTablePanel tablePanel = new ProductoTablePanel(productoDAO);

        add(tablePanel, BorderLayout.CENTER);
    }
}
