import com.formdev.flatlaf.FlatIntelliJLaf;
import gui.MainFrame;
import org.hibernate.SessionFactory;
import persistencia.HibernateUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main (String[] args) {

        // Set look and feel
        FlatIntelliJLaf.setup();
        JFrame.setDefaultLookAndFeelDecorated(true);
        UIManager.put("defaultFont", new Font("Verdana", Font.PLAIN, 14));

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
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
            MainFrame window = new MainFrame();
            window.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    if (!sessionFactory.isClosed()) {
                        sessionFactory.close();
                    }
                }
            });
            window.setVisible(true);
        });
    }
}