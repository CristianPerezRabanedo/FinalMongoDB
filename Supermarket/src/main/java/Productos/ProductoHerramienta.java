package Productos;

//Clase ProductoHerramienta
public class ProductoHerramienta extends Producto {
    private String material;
    private String tipoHerramienta; // Ejemplo: herramienta eléctrica, herramienta manual, etc.

    public ProductoHerramienta(String nombre, double precio, String descripcion, int cantidad, String material, String tipo, String marca) {
        super(nombre, precio, descripcion, cantidad, marca);
        this.material = material;
        this.tipoHerramienta = tipo;
    }

    // Métodos getter y setter para acceder y modificar los atributos
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getTipoHerramienta() {
        return tipoHerramienta;
    }

    public void setTipoHerramienta(String tipo) {
        this.tipoHerramienta = tipo;
    }
}