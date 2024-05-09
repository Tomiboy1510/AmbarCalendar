package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Profesional {

    @Id
    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private float porcentajeCobro;

    @Column(nullable = false)
    private int salarioBasico;

    public Profesional() {}
    public Profesional(String nombre, float porcentajeCobro, int salarioBasico) {
        this.nombre = nombre;
        this.porcentajeCobro = porcentajeCobro;
        this.salarioBasico = salarioBasico;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
}
