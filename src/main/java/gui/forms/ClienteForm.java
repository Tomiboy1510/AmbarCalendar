package gui.forms;

import entity.Cliente;
import persistence.dao.ClienteDAO;

import javax.swing.*;

public class ClienteForm extends EntityForm {

    private final JTextField dniField = new JTextField(20);
    private final JTextField nombreField = new JTextField(20);
    private final JTextField telefonoField = new JTextField(20);

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
        return null;
    }
}
