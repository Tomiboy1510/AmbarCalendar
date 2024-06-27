package entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sale of products provided by the business.
 */
@Entity
public class Venta extends Ingreso {

    /**
     * List of items comprising the sale
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ItemVenta> items;

    public Venta() {}

    @Override
    public int getMonto() {
        return items.stream()
                .mapToInt(ItemVenta::getMonto)
                .sum();
    }
    public List<ItemVenta> getItems() {
        return items;
    }
    public void setItems(List<ItemVenta> items) {
        this.items = new ArrayList<>(items);
    }
}
