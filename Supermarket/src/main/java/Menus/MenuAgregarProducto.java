package Menus;

import java.time.LocalDate;
import java.util.Scanner;
import Operaciones.OperacionesProducto;
import Productos.*;

public class MenuAgregarProducto {

    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean seguirAgregando = true;

        while (seguirAgregando) {
            System.out.println("Seleccione el tipo de producto que desea añadir:");
            System.out.println("1. Producto Alimenticio");
            System.out.println("2. Producto de Herramienta");
            System.out.println("3. Producto Electrodoméstico");
            System.out.println("4. Producto de Accesorio");
            System.out.println("5. Producto de Utensilio de Cocina");
            System.out.println("6. Volver al menú principal");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de leer el entero

            switch (opcion) {
                case 1:
                    OperacionesProducto.anadirProducto(crearProductoAlimenticio(scanner));
                    break;
                case 2:
                    OperacionesProducto.anadirProducto(crearProductoHerramienta(scanner));
                    break;
                case 3:
                    OperacionesProducto.anadirProducto(crearProductoElectrodomestico(scanner));
                    break;
                case 4:
                    OperacionesProducto.anadirProducto(crearProductoAccesorio(scanner));
                    break;
                case 5:
                    OperacionesProducto.anadirProducto(crearProductoUtensilioCocina(scanner));
                    break;
                case 6:
                    seguirAgregando = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static ProductoAlimenticio crearProductoAlimenticio(Scanner scanner) {
        System.out.println("Ingrese el nombre del producto alimenticio:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el precio del producto:");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea después de leer el double

        System.out.println("Ingrese la descripción del producto:");
        String descripcion = scanner.nextLine();

        System.out.println("Ingrese el peso del producto (en kg):");
        double peso = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea después de leer el double

        System.out.println("Ingrese la cantidad del producto:");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de leer el entero

        System.out.println("Ingrese la fecha de caducidad del producto (en formato YYYY-MM-DD):");
        String fechaCaducidadString = scanner.nextLine();

        LocalDate fechaCaducidad = LocalDate.parse(fechaCaducidadString);

        System.out.println("¿El producto es congelado? (true/false):");
        boolean esCongelado = scanner.nextBoolean();
        scanner.nextLine(); // Consumir el salto de línea después de leer el boolean

        System.out.println("Ingrese la marca del producto:");
        String marca = scanner.nextLine();

        return new ProductoAlimenticio(nombre, precio, descripcion, cantidad, peso, fechaCaducidad, esCongelado, marca);
    }

    private static ProductoHerramienta crearProductoHerramienta(Scanner scanner) {
        System.out.println("Ingrese el nombre del producto de herramienta:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el precio del producto:");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea después de leer el double

        System.out.println("Ingrese la descripción del producto:");
        String descripcion = scanner.nextLine();

        System.out.println("Ingrese el material del producto:");
        String material = scanner.nextLine();
        
        System.out.println("Ingrese la cantidad del producto:");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de leer el entero

        System.out.println("Ingrese el tipo del producto (herramienta eléctrica, herramienta manual, etc.):");
        String tipo = scanner.nextLine();

        System.out.println("Ingrese la marca del producto:");
        String marca = scanner.nextLine();

        return new ProductoHerramienta(nombre, precio, descripcion, cantidad, material, tipo, marca);
    }

    private static ProductoElectrodomestico crearProductoElectrodomestico(Scanner scanner) {
        System.out.println("Ingrese el nombre del producto electrodoméstico:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el precio del producto:");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea después de leer el double

        System.out.println("Ingrese la descripción del producto:");
        String descripcion = scanner.nextLine();
        
        System.out.println("Ingrese la cantidad del producto:");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de leer el entero

        System.out.println("Ingrese el tipo del producto (lavadora, nevera, televisión, etc.):");
        String tipoProducto = scanner.nextLine(); // Modificado para utilizar tipoProducto

        System.out.println("Ingrese la marca del producto:");
        String marca = scanner.nextLine();

        return new ProductoElectrodomestico(nombre, precio, descripcion, cantidad, tipoProducto, marca); // Modificado para utilizar tipoProducto
    }

    private static ProductoAccesorio crearProductoAccesorio(Scanner scanner) {
        System.out.println("Ingrese el nombre del producto de accesorio:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el precio del producto:");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea después de leer el double

        System.out.println("Ingrese la descripción del producto:");
        String descripcion = scanner.nextLine();
        
        System.out.println("Ingrese la cantidad del producto:");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de leer el entero

        System.out.println("Ingrese la categoría del producto (joyería, accesorios para el cabello, etc.):");
        String categoria = scanner.nextLine();

        System.out.println("Ingrese la marca del producto:");
        String marca = scanner.nextLine();

        return new ProductoAccesorio(nombre, precio, descripcion, cantidad, categoria, marca);
    }

    private static ProductoUtensilioCocina crearProductoUtensilioCocina(Scanner scanner) {
        System.out.println("Ingrese el nombre del producto de utensilio de cocina:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el precio del producto:");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea después de leer el double

        System.out.println("Ingrese la descripción del producto:");
        String descripcion = scanner.nextLine();

        System.out.println("Ingrese el material del producto:");
        String material = scanner.nextLine();
        
        System.out.println("Ingrese la cantidad del producto:");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de leer el entero

        System.out.println("Ingrese el uso del producto (cuchillo, sartén, olla, etc.):");
        String uso = scanner.nextLine();

        System.out.println("Ingrese la marca del producto:");
        String marca = scanner.nextLine();

        return new ProductoUtensilioCocina(nombre, precio, descripcion, cantidad, material, uso, marca);
    }
}
