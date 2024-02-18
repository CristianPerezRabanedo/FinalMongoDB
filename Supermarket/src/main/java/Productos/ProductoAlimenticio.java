package Productos;

import java.time.LocalDate;

//Clase ProductoAlimenticio
public class ProductoAlimenticio extends Producto {
    private double peso;
    private LocalDate fechaCaducidad;
    private boolean esCongelado;

    public ProductoAlimenticio(String nombre, double precio, String descripcion, int cantidad, double peso, LocalDate fechaCaducidad, boolean esCongelado, String marca) {
        super(nombre, precio, descripcion, cantidad, marca);
        this.peso = peso;
        this.fechaCaducidad = fechaCaducidad;
        this.esCongelado = esCongelado;
    }

    // Métodos getter y setter para acceder y modificar los atributos
    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public boolean isEsCongelado() {
        return esCongelado;
    }

    public void setEsCongelado(boolean esCongelado) {
        this.esCongelado = esCongelado;
    }
}