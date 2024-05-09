package gui;

import gui.tabs.AgendaPanel;
import gui.tabs.FinanzasPanel;
import gui.tabs.InventarioPanel;
import gui.tabs.PersonasPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends MyJFrame {

    public MainFrame() {
        super();

        setDefaultCloseOperation(MyJFrame.EXIT_ON_CLOSE);
        setTitle("Ámbar Beauty & Spa");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (0.7 * screenSize.getWidth()), (int) (0.8 * screenSize.getHeight()));
        setMinimumSize(new Dimension(800, 800));
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Agenda", new AgendaPanel());
        tabbedPane.addTab("Inventario", new InventarioPanel());
        tabbedPane.addTab("Finanzas", new FinanzasPanel());
        tabbedPane.addTab("Personas", new PersonasPanel());

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }
}