package entity;

import entity.enums.TipoPago;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Represents a sale of the business.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Ingreso implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * Date and time of the sale
     */
    @Column(nullable = false)
    private Date fechaHora;

    /**
     * Payment method used in the sale
     */
    @Enumerated(EnumType.STRING)
    private TipoPago tipoPago;

    public Ingreso() {}

    public abstract int getMonto();
    public Date getFechaHora() {
        return fechaHora;
    }
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }
    public TipoPago getTipoPago() {
        return tipoPago;
    }
    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
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
