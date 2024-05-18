package entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Venta extends Ingreso {

    @ElementCollection
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
        this.items = new ArrayList<>(items) {
            @Override
            public String toString() {
                return "Items";
            }
        };
    }
}
