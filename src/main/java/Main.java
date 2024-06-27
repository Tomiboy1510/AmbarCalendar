import gui.MainFrame;
import gui.SplashScreen;
import gui.UiUtils;
import org.hibernate.SessionFactory;
import persistence.HibernateUtil;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
    TODO
    - Pago de salarios
    - Agenda (registrar como subscriber!)
    - Añadir comentarios y Javadoc?
 */

public class Main {

    public static void main (String[] args) {

        // Setup look and feel
        UiUtils.setup();

        // Show splashscreen while establishing DB connection
        SplashScreen splashScreen = new SplashScreen();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        splashScreen.dispose();

        if (sessionFactory == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "No se pudo acceder a la instancia de base de datos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }

        // Create main window
        final MainFrame window = new MainFrame(sessionFactory);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!sessionFactory.isClosed()) {
                    sessionFactory.close();
                }
            }
        });

        // Set main window to fullscreen
        SwingUtilities.invokeLater(() -> {
            window.setExtendedState(JFrame.MAXIMIZED_BOTH);
            window.setVisible(true);
        });
    }
}