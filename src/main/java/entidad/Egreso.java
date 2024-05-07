package entidad;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Egreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String motivo;

    @Column(nullable = false)
    private int monto;

    @Column(nullable = false)
    private Date fecha;

    public Egreso() {}
    public Egreso(String motivo, int monto, Date fecha) {
        this.motivo = motivo;
        this.monto = monto;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
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
}
