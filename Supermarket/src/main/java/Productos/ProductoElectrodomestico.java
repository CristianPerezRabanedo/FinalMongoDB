package Productos;

//Clase ProductoElectrodomestico
public class ProductoElectrodomestico extends Producto {
    private String tipoProducto; // Cambio de "tipo" a "tipoProducto"

    public ProductoElectrodomestico(String nombre, double precio, String descripcion, int cantidad, String tipoProducto, String marca) {
        super(nombre, precio, descripcion, cantidad, marca);
        this.tipoProducto = tipoProducto; // Se asigna a "this.tipoProducto"
    }

    // Método setter modificado
    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    // Método getter sigue siendo el mismo
    public String getTipoProducto() {
        return tipoProducto;
    }
}
