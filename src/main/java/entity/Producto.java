package entity;

import entity.util.MyStringUtils;
import jakarta.persistence.*;

/**
 * Represents a product put up for sale by the business.
 */
@Entity
public class Producto implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * Name of the product. Maximum length: 50 characters
     */
    @Column(length = 60, nullable = false)
    private String nombre;

    /**
     * Brand of the product. Maximum length: 40 characters
     */
    @Column(length = 40, nullable = false)
    private String marca;

    /**
     * Cost of the product. Must be a non-negative integer
     */
    @Column(nullable = false)
    private int costo;

    /**
     * Selling price of the product. Must be a non-negative integer
     */
    @Column(nullable = false)
    private int precio;

    /**
     * Amount of units of the product in stock
     */
    @Column(nullable = false)
    private int stock;

    public Producto() {}

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = MyStringUtils.abbreviate(nombre, 60);
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = MyStringUtils.abbreviate(marca, 40);
    }
    public int getCosto() {
        return costo;
    }
    public void setCosto(int costo) {
        this.costo = costo;
    }
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
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
