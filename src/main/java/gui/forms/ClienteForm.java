package gui.forms;

import entity.Cliente;
import gui.formattedfields.DniField;
import gui.formattedfields.TelefonoField;
import persistence.dao.ClienteDAO;

import javax.swing.*;

public class ClienteForm extends EntityForm {

    private final DniField dniField = new DniField(20);
    private final JTextField nombreField = new JTextField(20);
    private final TelefonoField telefonoField = new TelefonoField(20);

    public ClienteForm(ClienteDAO dao) {
        super("Añadir Cliente", dao);
        init();
    }

    public ClienteForm(Cliente c, ClienteDAO dao) {
        super("Modificar Cliente", dao);
        isNew = false;
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
        return new Cliente(
                dniField.getText(),
                nombreField.getText(),
                telefonoField.getText()
        );
    }
}
