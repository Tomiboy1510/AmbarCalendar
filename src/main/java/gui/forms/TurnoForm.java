package gui.forms;

import entity.Cliente;
import entity.Profesional;
import entity.Turno;
import entity.enums.Servicio;
import entity.enums.TipoPago;
import gui.formattedfields.FechaField;
import gui.formattedfields.HoraField;
import gui.formattedfields.IntegerField;
import persistence.dao.ClienteDAO;
import persistence.dao.ProfesionalDAO;
import persistence.dao.TurnoDAO;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * Form for creating or modifying service sales ({@link Turno}).
 */
public class TurnoForm extends IngresoForm {

    private final FechaField fechaField = new FechaField(10);
    private final HoraField horaField = new HoraField(10);
    private final JComboBox<Servicio> servicioField = new JComboBox<>();
    private final JComboBox<Profesional> profesionalField = new JComboBox<>();
    private final JComboBox<Cliente> clienteField = new JComboBox<>();
    private final JTextField notasField = new JTextField(20);
    private final IntegerField montoField = new IntegerField(20);
    private final IntegerField montoPagadoField = new IntegerField(20);

    /**
     * DAO to get all customers
     */
    private final ClienteDAO clienteDAO;

    /**
     * DAO to get all professionals
     */
    private final ProfesionalDAO profesionalDAO;

    /**
     * Date format used for parsing dates
     */
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Date format used for parsing times
     */
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public TurnoForm(TurnoDAO turnoDAO, ClienteDAO clienteDAO, ProfesionalDAO profesionalDAO, Date defaultDate) {
        super("Registrar Turno", turnoDAO);
        this.clienteDAO = clienteDAO;
        this.profesionalDAO = profesionalDAO;
        init();

        fechaField.setText(dateFormat.format(defaultDate));
        horaField.setText(timeFormat.format(defaultDate));
    }

    /**
     * Constructor to create a form for modifying a service sale
     * @param t the service sale whose data will be used to fill the form
     * @param dao the DAO used to persist changes to the service sale
     */
    public TurnoForm(Turno t, TurnoDAO dao, ClienteDAO clienteDAO, ProfesionalDAO profesionalDAO) {
        super("Modificar Turno", t, dao);
        this.clienteDAO = clienteDAO;
        this.profesionalDAO = profesionalDAO;

        fechaField.setText(dateFormat.format(t.getFechaHora()));
        horaField.setText(timeFormat.format(t.getFechaHora()));
        servicioField.setSelectedItem(t.getServicio());
        profesionalField.setSelectedItem(t.getProfesional());
        clienteField.setSelectedItem(t.getCliente());
        montoField.setText(String.valueOf(t.getMonto()));
        montoPagadoField.setText(String.valueOf(t.getMontoPagado()));
        notasField.setText(t.getNotas());

        init();
    }

    protected void init() {
        super.init();

        addField("Monto total", montoField);
        addField("Monto Pagado", montoPagadoField);

        JPanel fechaHoraPanel = new JPanel();
        fechaHoraPanel.add(fechaField);
        fechaHoraPanel.add(horaField);

        addField("Fecha y Hora", fechaHoraPanel);
        addField("Servicio", servicioField);
        addField("Profesional a cargo", profesionalField);
        addField("Cliente", clienteField);
        addField("Notas adicionales", notasField);

        Arrays.stream(Servicio.values()).forEach(servicioField::addItem);

        // Set custom List Cell Renderer that shows a customer's name followed by their DNI number
        clienteField.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus
            ) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Cliente c = (Cliente) value;
                if (c != null)
                    label.setText(c.getNombre() + " (" + c.getDni() + ")");
                return label;
            }
        });

        // Set custom List Cell Renderer that shows a professional's name
        profesionalField.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus
            ) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Profesional p = (Profesional) value;
                if (p != null)
                    label.setText(p.getNombre());
                return label;
            }
        });

        // Get all customers and sort them by name
        clienteDAO.getAll().stream()
                .sorted(Comparator.comparing(Cliente::getNombre))
                .forEach(clienteField::addItem);

        // Get all professionals and sort them by name
        profesionalDAO.getAll().stream()
                .sorted(Comparator.comparing(Profesional::getNombre))
                .forEach(profesionalField::addItem);

        afterInit();
    }

    @Override
    protected Turno buildEntity() {
        Turno t = new Turno();
        t.setId(id);
        t.setServicio((Servicio) servicioField.getSelectedItem());
        t.setTipoPago((TipoPago) tipoPagoField.getSelectedItem());
        t.setNotas(notasField.getText());
        try {
            t.setMonto(Integer.parseInt(montoField.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Monto obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        try {
            t.setMontoPagado(Integer.parseInt(montoPagadoField.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Monto pagado obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        try {
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat(dateFormat.toPattern() + " " + timeFormat.toPattern());
            dateTimeFormat.setLenient(false);
            t.setFechaHora(dateTimeFormat
                    .parse(fechaField.getText() + " " + horaField.getText())
            );
        } catch (Exception e) {
            t.setFechaHora(null);
        }
        t.setCliente((Cliente) clienteField.getSelectedItem());
        t.setProfesional((Profesional) profesionalField.getSelectedItem());

        return t;
    }
}
