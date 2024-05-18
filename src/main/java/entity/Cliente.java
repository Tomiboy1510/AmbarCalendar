package entity;

import entity.util.MyUtils;
import jakarta.persistence.*;

@Entity
public class Cliente implements AbstractEntity {

    private static final String[] fieldNames = {
            "dni", "nombre", "telefono"
    };
    private static final String[] fieldNamesButPretty = {
            "DNI", "Nombre", "Teléfono"
    };

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private int dni;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 25, nullable = false)
    private String telefono;

    public Cliente() {}

    public int getDni() {
        return dni;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = MyUtils.abbreviate(nombre, 50);
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = MyUtils.abbreviate(telefono, 25);
    }

    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }

    public static String[] getFieldNames() {
        return fieldNames;
    }
    public static String[] getFieldNamesButPretty() {
        return fieldNamesButPretty;
    }
}
