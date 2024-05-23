package entity;

import entity.util.MyStringUtils;
import jakarta.persistence.*;

@Entity
public class Cliente implements AbstractEntity {

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
        this.nombre = MyStringUtils.abbreviate(nombre, 50);
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = MyStringUtils.abbreviate(telefono, 25);
    }

    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
}
