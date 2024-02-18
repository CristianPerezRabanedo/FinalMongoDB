package Menus;

import java.util.Scanner;
import Operaciones.OperacionesProducto;

public class MenuModificarProducto {

    public static void mostrarMenu(Scanner scanner) {
        boolean seguirModificando = true;

        while (seguirModificando) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Modificar producto");
            System.out.println("2. Volver al menú principal");
            System.out.print("Ingrese su opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de leer el entero

            switch (opcion) {
                case 1:
                    OperacionesProducto.modificarProducto(scanner);
                    break;
                case 2:
                    seguirModificando = false;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }
}
