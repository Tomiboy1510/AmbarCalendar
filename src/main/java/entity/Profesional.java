package entity;

import entity.util.MyStringUtils;
import jakarta.persistence.*;

/**
 * Represents a professional working for the business.
 */
@Entity
public class Profesional implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * Name and surname of the professional. Maximum length: 50 characters
     */
    @Column(length = 50, nullable = false)
    private String nombre;

    /**
     * Commission rate.
     * Must be an integer in the range [0, 100)
     */
    @Column(nullable = false)
    private float porcentajeCobro;

    /**
     * Base salary. Must be a positive integer
     */
    @Column(nullable = false)
    private int salarioBasico;

    public Profesional() {}

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = MyStringUtils.abbreviate(nombre, 50);
    }
    public float getPorcentajeCobro() {
        return porcentajeCobro;
    }
    public void setPorcentajeCobro(float porcentajeCobro) {
        this.porcentajeCobro = porcentajeCobro;
    }
    public int getSalarioBasico() {
        return salarioBasico;
    }
    public void setSalarioBasico(int salarioBasico) {
        this.salarioBasico = salarioBasico;
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
