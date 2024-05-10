package gui.forms;

import entity.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteForm extends EntityForm {

    private final JTextField dniField = new JTextField(20);
    private final JTextField nombreField = new JTextField(20);
    private final JTextField telefonoField = new JTextField(20);
    private boolean isNew = true;

    public ClienteForm() {
        super("Añadir Cliente");
        init();
    }

    public ClienteForm(Cliente c) {
        super("Modificar Cliente");
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
    protected ActionListener getSaveButtonHandler() {
        return (ActionEvent e) -> {

        };
    }
}
