package entity;

import entity.util.MyStringUtils;
import jakarta.persistence.*;

/**
 * Represents a customer of the business.
 */
@Entity
public class Cliente implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * National Identity Document number. Must be a non-negative integer
     */
    @Column(nullable = false)
    private int dni;

    /**
     * Name and surname of the customer. Maximum length: 50 characters
     */
    @Column(length = 50, nullable = false)
    private String nombre;

    /**
     * Telephone number of the customer. Maximum length: 25 characters
     */
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
