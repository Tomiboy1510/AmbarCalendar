package entity;

import entity.util.MyUtils;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Egreso implements AbstractEntity {

    private static final String[] fieldNames = {
            "motivo", "monto", "fecha"
    };
    private static final String[] fieldNamesButPretty = {
            "Motivo", "Monto", "Fecha"
    };

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
        this.motivo = MyUtils.abbreviate(motivo, 50);
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

    public static String[] getFieldNames() {
        return fieldNames;
    }
    public static String[] getFieldNamesButPretty() {
        return fieldNamesButPretty;
    }
}
