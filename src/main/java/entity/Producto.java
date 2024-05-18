package entity;

import entity.util.MyUtils;
import jakarta.persistence.*;

@Entity
public class Producto implements AbstractEntity {

    private static final String[] fieldNames = {
            "nombre", "marca", "costo", "precio", "stock"
    };
    private static final String[] fieldNamesButPretty = {
            "Nombre", "Marca", "Costo", "Precio", "Stock"
    };

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

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

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = MyUtils.abbreviate(nombre, 60);
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = MyUtils.abbreviate(marca, 40);
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

    public static String[] getFieldNames() {
        return fieldNames;
    }
    public static String[] getFieldNamesButPretty() {
        return fieldNamesButPretty;
    }
}
