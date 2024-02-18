package Menus;

import java.util.Scanner;
import Operaciones.OperacionesProducto;

public class MenuBuscarProducto {

    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
            boolean seguirBuscando = true;

            while (seguirBuscando) {
                System.out.println("Seleccione el criterio de búsqueda:");
                System.out.println("");
                System.out.println("1. Buscar por nombre");
                System.out.println("2. Buscar por marca");
                System.out.println("3. Buscar por cantidad mayor que");
                System.out.println("4. Buscar por cantidad menor que");
                System.out.println("5. Buscar por precio mayor que");
                System.out.println("6. Buscar por precio menor que");
                System.out.println("7. Volver al menu principal");
                System.out.println("");
                System.out.print("Seleccione una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea después de leer el entero

                int unidades = 0;
                int precio = 0;
                
                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese el nombre o parte del nombre a buscar:");
                        String nombre = scanner.nextLine();
                        OperacionesProducto.buscarPorNombre(nombre);
                        break;
                    case 2:
                        System.out.println("Ingrese la marca a buscar:");
                        String marca = scanner.nextLine();
                        OperacionesProducto.buscarPorMarca(marca);
                        break;
                    case 3:
                        System.out.println("Ingrese la cantidad de unidades mayor o igual que la desea buscar:");
                        unidades = scanner.nextInt();
                        scanner.nextLine(); // Consumir el salto de línea después de leer el entero
                        OperacionesProducto.buscarPorCantidadMayorQue(unidades);
                        break;
                    case 4:
                        System.out.println("Ingrese la cantidad de unidades menor o igual que la que desea buscar:");
                        unidades = scanner.nextInt();
                        scanner.nextLine(); // Consumir el salto de línea después de leer el entero
                        OperacionesProducto.buscarPorCantidadMenorQue(unidades);
                        break;
                    case 5:
                        System.out.println("Ingrese el precio (en euros) mayor o igual que el desea buscar:");
                        precio = scanner.nextInt();
                        scanner.nextLine(); // Consumir el salto de línea después de leer el entero
                        OperacionesProducto.buscarPorPrecioMayorQue(precio);
                        break;
                    case 6:
                        System.out.println("Ingrese el precio (en euros) menor o igual que el desea buscar:");
                        precio = scanner.nextInt();
                        scanner.nextLine(); // Consumir el salto de línea después de leer el entero
                        OperacionesProducto.buscarPorPrecioMenorQue(precio);
                        break;
                    case 7:
                        seguirBuscando = false;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
    }
}

