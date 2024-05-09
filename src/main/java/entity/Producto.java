package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Producto {

    @Id
    @Column(length = 60, nullable = false)
    private String nombre;

    @Column(length = 40, nullable = false)
    private String marca;

    @Column(nullable = false)
    private int costo;

    @Column(nullable = false)
    private int precio;

    @Column(nullable = false)
    private int stock;

    public Producto() {}
    public Producto(String nombre, String marca, int costo, int precio, int stock) {
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.precio = precio;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
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
}
