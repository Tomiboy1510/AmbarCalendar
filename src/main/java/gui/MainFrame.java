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
        setSize((int) (0.7 * screenSize.getWidth()), (int) (0.7 * screenSize.getHeight()));
        setMinimumSize(new Dimension(800, 800));
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        ProductoDAO productoDAO = new ProductoDAO(sessionFactory);
        ItemVentaDAO itemVentaDAO = new ItemVentaDAO(sessionFactory, productoDAO);
        VentaDAO ventaDAO = new VentaDAO(sessionFactory, itemVentaDAO);
        EgresoDAO egresoDAO = new EgresoDAO(sessionFactory);
        ClienteDAO clienteDAO = new ClienteDAO(sessionFactory);
        ProfesionalDAO profesionalDAO = new ProfesionalDAO(sessionFactory);
        TurnoDAO turnoDAO = new TurnoDAO(sessionFactory, clienteDAO, profesionalDAO);

        tabbedPane.addTab("Agenda", new AgendaTab(turnoDAO));

        tabbedPane.addTab("Inventario", new InventarioTab(productoDAO));

        tabbedPane.addTab("Finanzas", new FinanzasTab(
                turnoDAO,
                clienteDAO,
                profesionalDAO,
                ventaDAO,
                productoDAO,
                egresoDAO)
        );

        tabbedPane.addTab("Personas", new PersonasTab(
                clienteDAO,
                profesionalDAO)
        );

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }
}