package Productos;

//Clase ProductoAccesorio
public class ProductoAccesorio extends Producto {
    private String categoria; // Ejemplo: joyería, accesorios para el cabello, etc.

    public ProductoAccesorio(String nombre, double precio, String descripcion, int cantidad, String categoria, String marca) {
        super(nombre, precio, descripcion, cantidad, marca);
        this.categoria = categoria;
    }

    // Métodos getter y setter para acceder y modificar los atributos
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
