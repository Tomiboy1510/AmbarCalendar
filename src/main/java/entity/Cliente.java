package entity;

import entity.util.MyUtils;
import jakarta.persistence.*;

@Entity
public class Cliente implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(length = 20, nullable = false)
    private String dni;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 25, nullable = false)
    private String telefono;

    public Cliente() {}

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = MyUtils.abbreviate(dni, 20);
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
}
