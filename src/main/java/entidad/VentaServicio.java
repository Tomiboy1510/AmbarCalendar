package entidad;

import enums.TipoPago;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class VentaServicio extends Ingreso {

    @ManyToOne
    private Profesional profesional;

    @ManyToOne
    private Cliente cliente;

    @Column(nullable = false)
    private int montoPagado;

    @Column
    private String notas;

    public VentaServicio() {}
    public VentaServicio(int monto,
                         Date fechaHora,
                         TipoPago tipoPago,
                         Profesional profesional,
                         Cliente cliente,
                         int montoPagado,
                         String notas
    ) {
        super(monto, fechaHora, tipoPago);
        this.profesional = profesional;
        this.cliente = cliente;
        this.montoPagado = montoPagado;
        this.notas = notas;
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
        this.notas = notas;
    }
}
