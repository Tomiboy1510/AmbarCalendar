package entidad;

import enums.TipoPago;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Ingreso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private int monto;

    @Column(nullable = false)
    private Date fechaHora;

    @Enumerated(EnumType.ORDINAL)
    private TipoPago tipoPago;

    public Ingreso() {}
    public Ingreso(int monto, Date fechaHora, TipoPago tipoPago) {
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.tipoPago = tipoPago;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getMonto() {
        return monto;
    }
    public void setMonto(int monto) {
        this.monto = monto;
    }
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
}
