package entidad;

import enums.TipoPago;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class VentaProducto extends Ingreso {

    @ElementCollection
    private List<ItemVenta> items;

    public VentaProducto() {}
    public VentaProducto(int monto, Date fechaHora, TipoPago tipoPago, List<ItemVenta> items) {
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
