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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class TurnoForm extends IngresoForm {

    private final FechaField fechaField = new FechaField(10);
    private final HoraField horaField = new HoraField(10);
    private final JComboBox<Servicio> servicioField = new JComboBox<>();
    private final JComboBox<Profesional> profesionalField = new JComboBox<>();
    private final JComboBox<Cliente> clienteField = new JComboBox<>();
    private final JTextField notasField = new JTextField(20);
    private final IntegerField montoPagadoField = new IntegerField(20);

    private final ClienteDAO clienteDAO;
    private final ProfesionalDAO profesionalDAO;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public TurnoForm(TurnoDAO turnoDAO, ClienteDAO clienteDAO, ProfesionalDAO profesionalDAO, Date defaultDate) {
        super("Registrar Turno", turnoDAO);
        this.clienteDAO = clienteDAO;
        this.profesionalDAO = profesionalDAO;
        init();

        fechaField.setText(dateFormat.format(defaultDate));
        horaField.setText(timeFormat.format(defaultDate));
    }

    public TurnoForm(Turno t, TurnoDAO dao, ClienteDAO clienteDAO, ProfesionalDAO profesionalDAO) {
        super("Modificar Turno", t, dao);
        this.clienteDAO = clienteDAO;
        this.profesionalDAO = profesionalDAO;
        isNew = false;

        fechaField.setText(dateFormat.format(t.getFechaHora()));
        horaField.setText(timeFormat.format(t.getFechaHora()));
        servicioField.setSelectedItem(t.getServicio());
        profesionalField.setSelectedItem(t.getProfesional());
        clienteField.setSelectedItem(t.getCliente());
        montoPagadoField.setText(String.valueOf(t.getMontoPagado()));
        notasField.setText(t.getNotas());

        init();
    }

    protected void init() {
        super.init();

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

        clienteField.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus
            ) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Cliente c = ((Cliente) value);
                if (c != null)
                    label.setText(c.getNombre() + " (" + c.getDni() + ")");
                return label;
            }
        });

        profesionalField.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus
            ) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Profesional c = ((Profesional) value);
                if (c != null)
                    label.setText(c.getNombre());
                return label;
            }
        });

        clienteDAO.getAll()
                .forEach(clienteField::addItem);
        profesionalDAO.getAll()
                .forEach(profesionalField::addItem);

        afterInit();
    }

    @Override
    protected Turno buildEntity() {
        Turno t = new Turno();
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
            t.setFechaHora(new SimpleDateFormat(dateFormat.toPattern() + " " + timeFormat.toPattern())
                    .parse(fechaField.getText() + " " + horaField.getText())
            );
        } catch (Exception e) {
            t.setFechaHora(null);
        }
        t.setCliente(clienteDAO.get((
                (Cliente) Objects.requireNonNull(clienteField.getSelectedItem())).getDni()
        ));
        t.setProfesional(profesionalDAO.get((
                (Profesional) Objects.requireNonNull(profesionalField.getSelectedItem())).getNombre()
        ));
        return t;
    }
}
