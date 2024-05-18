package entity;

import entity.enums.Servicio;
import entity.util.MyUtils;
import jakarta.persistence.*;

@Entity
public class Turno extends Ingreso {

    private static final String[] fieldNames = {
            "fechaHora", "tipoPago", "monto", "servicio", "profesional", "cliente", "montoPagado", "notas"
    };
    private static final String[] fieldNamesButPretty = {
            "Fecha y Hora", "Tipo de Pago", "Monto", "Servicio", "Profesional", "Cliente", "Monto Pagado", "Notas"
    };

    @Column(nullable = false)
    private int monto;

    @Enumerated(EnumType.STRING)
    private Servicio servicio;

    @ManyToOne
    private Profesional profesional;

    @ManyToOne
    private Cliente cliente;

    @Column(nullable = false)
    private int montoPagado;

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
        this.notas = MyUtils.abbreviate(notas, 100);
    }

    public static String[] getFieldNames() {
        return fieldNames;
    }
    public static String[] getFieldNamesButPretty() {
        return fieldNamesButPretty;
    }
}
