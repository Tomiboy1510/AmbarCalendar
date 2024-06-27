package entity;

import jakarta.persistence.*;

/**
 * Represents an item in a sale of products.
 */
@Entity
public class ItemVenta implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * Product sold
     */
    @ManyToOne(optional = false)
    private Producto producto;

    /**
     * Quantity of the product sold. Must be a positive integer
     */
    @Column(nullable = false)
    private int cantidad;

    /**
     * Amount of money charged. Must be a non-negative integer
     */
    @Column(nullable = false)
    private int monto;

    public ItemVenta() {}

    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public int getMonto() {
        return monto;
    }
    public void setMonto(int monto) {
        this.monto = monto;
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
