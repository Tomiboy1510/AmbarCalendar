package entity;

import entity.util.MyStringUtils;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Represents an expense of the business.
 */
@Entity
public class Egreso implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * Description or name for the reason of the expense. Maximum length: 50 characters
     */
    @Column(length = 50, nullable = false)
    private String motivo;

    /**
     * Amount of money expended. Must be a non-negative integer
     */
    @Column(nullable = false)
    private int monto;

    /**
     * Date of the expense
     */
    @Column(nullable = false)
    private Date fecha;

    public Egreso() {}

    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = MyStringUtils.abbreviate(motivo, 50);
    }
    public int getMonto() {
        return monto;
    }
    public void setMonto(int monto) {
        this.monto = monto;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
