package entity;

import entity.enums.Servicio;
import entity.util.MyStringUtils;
import jakarta.persistence.*;

/**
 * Represents a sale of a service provided by the business.
 */
@Entity
public class Turno extends Ingreso {

    /**
     * Amount of money charged by the service. Must be a non-negative integer
     */
    @Column(nullable = false)
    private int monto;

    /**
     * Type of service provided
     */
    @Enumerated(EnumType.STRING)
    private Servicio servicio;

    /**
     * Professional that provided the service
     */
    @ManyToOne
    private Profesional profesional;

    /**
     * Customer that bought the service
     */
    @ManyToOne
    private Cliente cliente;

    /**
     * Amount of money that the customer has paid so far. Must be a non-negative integer
     */
    @Column(nullable = false)
    private int montoPagado;

    /**
     * Additional notes
     */
    @Column(length = 100)
    private String notas;

    public Turno() {}

    @Override
    public int getMonto() {
        return monto;
    }
    public void setMonto(int monto) {
        this.monto = monto;
    }
    public Servicio getServicio() {
        return servicio;
    }
    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
    public Profesional getProfesional() {
        return profesional;
    }
    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public int getMontoPagado() {
        return montoPagado;
    }
    public void setMontoPagado(int montoPagado) {
        this.montoPagado = montoPagado;
    }
    public String getNotas() {
        return notas;
    }
    public void setNotas(String notas) {
        this.notas = MyStringUtils.abbreviate(notas, 100);
    }
}
