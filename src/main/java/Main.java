import com.formdev.flatlaf.FlatIntelliJLaf;
import entidad.Cliente;
import gui.MainWindow;
import org.hibernate.SessionFactory;
import persistencia.HibernateDAO;
import persistencia.HibernateUtil;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main (String[] args) {

        // Set look and feel
        FlatIntelliJLaf.setup();

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

        //new HibernateDAO<Cliente>(sessionFactory, Cliente.class).save(new Cliente("nigger","nigger","nigger"));

        /*SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow();
            window.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    if (!sessionFactory.isClosed()) {
                        sessionFactory.close();
                    }
                }
            });
            window.setVisible(true);
        });*/
    }
}