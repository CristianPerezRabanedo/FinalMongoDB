package Menus;

import java.util.Scanner;
import Operaciones.OperacionesProducto;

public class MenuEliminarProducto {

    public static void mostrarMenu() {
        Scanner input = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("Seleccione las diferentes opciones de borrado.");
            System.out.println("");
            System.out.println("1. Eliminar producto por nombre");
            System.out.println("2. Filtrar y eliminar un producto por marca");
            System.out.println("3. Filtrar y eliminar por cantidad mayor que");
            System.out.println("4. Filtrar y eliminar por cantidad menor que");
            System.out.println("5. Filtrar y eliminar por precio un producto con la cantidad igual o mayor que la que usted indique");
            System.out.println("6. Filtrar y eliminar por precio un producto con la cantidad igual o menor que la que usted indique");
            System.out.println("7. Eliminar todos los productos de una marca.");
            System.out.println("8. Eliminar todos los productos con una cantidad igual o mayor a la que usted indique.");
            System.out.println("9. Eliminar todos los productos con una cantidad igual o menor a la que usted indique.");
            System.out.println("10. Eliminar todos los productos con un precio igual o mayor al que usted indique.");
            System.out.println("11. Eliminar todos los productos con un precio igual o menor al que usted indique.");
            System.out.println("12. Volver al menu principal");
            System.out.println("");
            System.out.print("Seleccione una opción: ");

            int opcion = input.nextInt();
            input.nextLine(); // Limpiar el buffer de entrada
            
            int unidades = 0;
            int precio = 0;
            String marca;

            switch (opcion) {
                case 1:
                    System.out.print("Introduzca el nombre del producto que desea eliminar: ");
                    String nombreProducto = input.nextLine();
                    System.out.println("");
                    System.out.println("Ha indicado que el nombre del producto que desea eliminar es " + nombreProducto);
                    System.out.println("");
                    OperacionesProducto.eliminarPorNombre(nombreProducto);
                    break;
                case 2:
                    System.out.print("Introduzca la marca del producto que desea eliminar: ");
                    marca = input.nextLine();
                    System.out.println("Ha indicado que el nombre del producto con la marca que desea eliminar es " + marca);
                    System.out.println("");
                    OperacionesProducto.eliminarPorMarca(marca);
                    break;
                case 3:
                    System.out.print("Ingrese la cantidad mayor que desea buscar:");
                    unidades = input.nextInt();
                    System.out.println("");
                    System.out.print("Ha indicado que quiere filtrar y eliminar un producto con una cantidad mayor o igual a " + unidades +" unidades.");
                    System.out.println("");
                    OperacionesProducto.eliminarPorCantidadMayorQue(unidades);
                    break;
                case 4:
                	System.out.print("Ingrese la cantidad menor que desea buscar:");
                    unidades = input.nextInt();
                    System.out.println("");
                    System.out.print("Ha indicado que quiere filtrar y eliminar un producto con una cantidad menor o igual a " + unidades +" unidades.");
                    System.out.println("");
                    OperacionesProducto.eliminarPorCantidadMenorQue(unidades);
                    break;
                case 5:
                    System.out.print("Ingrese el precio (en euros) mayor o igual que el que desea buscar:");
                    precio = input.nextInt();
                    System.out.println("");
                    System.out.print("Ha indicado que quiere filtrar y eliminar un producto con el precio mayor o igual a " + precio +" euros.");
                    System.out.println("");
                    OperacionesProducto.eliminarPorPrecioMayorQue(precio);
                    break;
                case 6:
                    System.out.print("Ingrese el precio (en euros) menor o igual que el del producto que desea eliminar:");
                    precio = input.nextInt();
                    System.out.println("");
                    System.out.print("Ha indicado que quiere filtrar y eliminar un producto con el precio menor o igual a " + precio +" euros.");
                    System.out.println("");
                    OperacionesProducto.eliminarPorPrecioMenorQue(precio);
                    break;
                case 7:
                    System.out.print("Introduzca la marca de los productos que desea eliminar: ");
                    marca = input.nextLine();
                    System.out.println("");
                    System.out.println("Ha indicado que quiere eliminar todos los productos con la marca " + marca + ".");
                    System.out.println("");
                    OperacionesProducto.eliminarProductosMarca(marca);
                    break;
                case 8:
                    System.out.print("Ingrese una cantidad para eliminar todos los productos con dicha cantidad o mayor a la que usted indique.");
                    unidades = input.nextInt();
                    System.out.println("");
                    System.out.println("Ha indicado que quiere eliminar todos los productos con una cantidad de unidades igual o mayor a " + unidades + ".");
                    System.out.println("");
                    OperacionesProducto.eliminarProductosCantidadMayor(unidades);
                    break;
                case 9:
                    System.out.print("Ingrese una cantidad para eliminar todos los productos con dicha cantidad o menor a la que usted indique.");
                    unidades = input.nextInt();
                    System.out.println("");
                    System.out.println("Ha indicado que quiere eliminar todos los productos con una cantidad de unidades igual o menor a " + unidades + ".");
                    System.out.println("");
                    OperacionesProducto.eliminarProductosCantidadMenor(unidades);
                    break;
                case 10:
                    System.out.print("Ingrese un precio para eliminar todos los productos con dicho precio o mayor al que usted indique.");
                    precio = input.nextInt();
                    System.out.println("");
                    System.out.println("Ha indicado que quiere eliminar todos los productos con un precio igual o mayor a " + precio + " euros.");
                    System.out.println("");
                    OperacionesProducto.eliminarProductosPrecioMayor(precio);
                    break;
                case 11:
                    System.out.print("Ingrese un precio para eliminar todos los productos con dicho precio o menor al que usted indique.");
                    precio = input.nextInt();
                    System.out.println("");
                    System.out.println("Ha indicado que quiere eliminar todos los productos con un precio igual o menor a " + precio + " euros.");
                    System.out.println("");
                    OperacionesProducto.eliminarProductosPrecioMenor(precio);
                    break;
                case 12:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }
}
