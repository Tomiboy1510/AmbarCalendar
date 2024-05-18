package entity;

import entity.util.MyUtils;
import jakarta.persistence.*;

@Entity
public class Profesional implements AbstractEntity {

    private static final String[] fieldNames = {
            "nombre", "porcentajeCobro", "salarioBasico"
    };
    private static final String[] fieldNamesButPretty = {
            "Nombre", "Porcentaje de Cobro", "Salario Básico"
    };

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private float porcentajeCobro;

    @Column(nullable = false)
    private int salarioBasico;

    public Profesional() {}

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = MyUtils.abbreviate(nombre, 50);
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

    public static String[] getFieldNames() {
        return fieldNames;
    }
    public static String[] getFieldNamesButPretty() {
        return fieldNamesButPretty;
    }
}
