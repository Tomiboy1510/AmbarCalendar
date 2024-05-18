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
    - Informe de finanzas
    - Agenda (registrar como subscriber!)
    - Formulario de ventas
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

        SwingUtilities.invokeLater(() -> {
            MainFrame window = new MainFrame(sessionFactory);
            window.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    if (!sessionFactory.isClosed()) {
                        sessionFactory.close();
                    }
                }
            });
            //Set frame to fullscreen
            window.setExtendedState(JFrame.MAXIMIZED_BOTH);
            window.setVisible(true);
        });
    }
}