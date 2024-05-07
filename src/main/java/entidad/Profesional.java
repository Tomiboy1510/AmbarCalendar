package entidad;

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

    public Profesional() {}
    public Profesional(String nombre, float porcentajeCobro) {
        this.nombre = nombre;
        this.porcentajeCobro = porcentajeCobro;
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
}
