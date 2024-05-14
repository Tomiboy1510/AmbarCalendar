package entity;

import entity.enums.TipoPago;
import gui.forms.IngresoForm;
import gui.forms.VentaForm;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Venta extends Ingreso {

    @ElementCollection
    private List<ItemVenta> items;

    public Venta() {}

    public Venta(int monto, Date fechaHora, TipoPago tipoPago, List<ItemVenta> items) {
        super(monto, fechaHora, tipoPago);
        this.items = items;
    }

    public List<ItemVenta> getItems() {
        return items;
    }
    public void setItems(List<ItemVenta> items) {
        this.items = items;
    }
}
