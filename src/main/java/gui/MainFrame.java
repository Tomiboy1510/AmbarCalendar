package gui;

import gui.tabs.AgendaTab;
import gui.tabs.FinanzasTab;
import gui.tabs.InventarioTab;
import gui.tabs.PersonasTab;
import org.hibernate.SessionFactory;
import persistence.dao.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends MyJFrame {

    public MainFrame(SessionFactory sessionFactory) {
        super();

        setDefaultCloseOperation(MyJFrame.EXIT_ON_CLOSE);
        setTitle("Ámbar Beauty & Spa");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (0.9 * screenSize.getWidth()), (int) (0.9 * screenSize.getHeight()));
        setMinimumSize(new Dimension(800, 800));
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        TurnoDAO turnoDAO = new TurnoDAO(sessionFactory);

        tabbedPane.addTab("Agenda", new AgendaTab(turnoDAO));

        tabbedPane.addTab("Inventario", new InventarioTab(new ProductoDAO(sessionFactory)));

        tabbedPane.addTab("Finanzas", new FinanzasTab(
                turnoDAO,
                new VentaDAO(sessionFactory),
                new EgresoDAO(sessionFactory))
        );

        tabbedPane.addTab("Personas", new PersonasTab(
                new ClienteDAO(sessionFactory),
                new ProfesionalDAO(sessionFactory))
        );

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }
}