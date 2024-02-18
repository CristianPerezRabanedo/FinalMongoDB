package Productos;

//Clase ProductoUtensilioCocina
public class ProductoUtensilioCocina extends Producto {
    private String material;
    private String uso; // Ejemplo: cuchillo, sartén, olla, etc.

    public ProductoUtensilioCocina(String nombre, double precio, String descripcion, int cantidad, String material, String uso, String marca) {
        super(nombre, precio, descripcion, cantidad, marca);
        this.material = material;
        this.uso = uso;
    }

    // Métodos getter y setter para acceder y modificar los atributos
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }
}