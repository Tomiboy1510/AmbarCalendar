package gui.forms;

import entity.Cliente;
import gui.formattedfields.IntegerField;
import gui.formattedfields.TelefonoField;
import persistence.dao.ClienteDAO;

import javax.swing.*;

public class ClienteForm extends EntityForm {

    private final IntegerField dniField = new IntegerField(20);
    private final JTextField nombreField = new JTextField(20);
    private final TelefonoField telefonoField = new TelefonoField(20);

    public ClienteForm(ClienteDAO dao) {
        super("Añadir Cliente", dao);
        init();
    }

    public ClienteForm(Cliente c, ClienteDAO dao) {
        super("Modificar Cliente", dao, c.getId());
        isNew = false;

        dniField.setText(String.valueOf(c.getDni()));
        nombreField.setText(c.getNombre());
        telefonoField.setText(c.getTelefono());

        init();
    }

    private void init() {
        addField("DNI", dniField);
        addField("Nombre", nombreField);
        addField("Teléfono", telefonoField);

        afterInit();
    }

    @Override
    protected Cliente buildEntity() {
        Cliente c = new Cliente();
        c.setId(id);
        try {
            c.setDni(Integer.parseInt(dniField.getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "DNI obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        c.setNombre(nombreField.getText());
        c.setTelefono(telefonoField.getText());
        return c;
    }
}
