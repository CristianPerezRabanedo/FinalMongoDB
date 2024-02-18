package Menus;

import java.util.Scanner;
import Operaciones.OperacionesProducto;

public class MenuPrincipal {

    public static void mostrarMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Seleccione una opción:");
                System.out.println("1. Buscar un producto");
                System.out.println("2. Agregar un producto");
                System.out.println("3. Modificar un producto");
                System.out.println("4. Eliminar un producto");
                System.out.println("5. Mostrar todos los productos");
                System.out.println("6. Salir");

                System.out.print("Ingrese su opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea después de leer el entero

                switch (opcion) {
                    case 1:
                        System.out.println("Ha seleccionado la opción << Buscar producto >>.\n");
                        MenuBuscarProducto.mostrarMenu();
                        break;
                    case 2:
                        System.out.println("Ha seleccionado la opción << Agregar producto >>.\n");
                        MenuAgregarProducto.mostrarMenu();
                        break;
                    case 3:
                        System.out.println("Ha seleccionado la opción << Modificar producto >>.\n");
                        MenuModificarProducto.mostrarMenu(scanner);
                        break;
                    case 4:
                        System.out.println("Ha seleccionado la opción << Eliminar producto >>.\n");
                        MenuEliminarProducto.mostrarMenu();
                        break;
                    case 5:
                        System.out.println("Ha seleccionado la opción << Mostrar todos los productos >>.\n");
                        OperacionesProducto.mostrarTodosLosProductos();
                        break;
                    case 6:
                        System.out.println("Ha seleccionado la opción << Salir >>.");
                        System.out.println("¡Hasta luego!");
                        return;
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción válida.\n");
                        break;
                }
            }
        }
    }
}
