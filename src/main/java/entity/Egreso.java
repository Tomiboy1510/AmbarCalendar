package entity;

import entity.util.MyStringUtils;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Egreso implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 50, nullable = false)
    private String motivo;

    @Column(nullable = false)
    private int monto;

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
