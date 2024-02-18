package Operaciones;

import Productos.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import Menus.MenuPrincipal;
import MongoDB.ConexionMongoDB;

import java.util.regex.Pattern;
import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.eq;


public class OperacionesProducto {

    private static final String NOMBRE_COLECCION = "productos";
    private static final Scanner scanner = new Scanner(System.in);

    
    //METODO PARA AÃ‘ADIR PRODUCTOS A LA BBDD
    
    public static void anadirProducto(Producto producto) {

        // Obtener el cliente de MongoDB desde la clase de conexiÃ³n
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();

        // Verificar si la conexiÃ³n estÃ¡ establecida
        if (ConexionMongoDB.isConnected()) {
            try {
                // Obtener la base de datos
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");

                // Obtener la colecciÃ³n (tabla)
                MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

                // Convertir el objeto Producto a un documento BSON
                Document docProducto = new Document()
                        .append("nombre", producto.getNombre())
                        .append("precio", producto.getPrecio())
                        .append("descripcion", producto.getDescripcion())
                        .append("marca", producto.getMarca())
                        .append("cantidad", producto.getCantidad());

                // AÃ±adir atributos especÃ­ficos del tipo de producto
                if (producto instanceof ProductoAlimenticio) {
                    ProductoAlimenticio productoAlimenticio = (ProductoAlimenticio) producto;
                    docProducto.append("peso", productoAlimenticio.getPeso())
                            .append("fechaCaducidad", productoAlimenticio.getFechaCaducidad().toString())
                            .append("esCongelado", productoAlimenticio.isEsCongelado())
                            .append("tipo", "alimenticio");
                } else if (producto instanceof ProductoHerramienta) {
                    ProductoHerramienta productoHerramienta = (ProductoHerramienta) producto;
                    docProducto.append("material", productoHerramienta.getMaterial())
                            .append("tipo herramienta", productoHerramienta.getTipoHerramienta())
                            .append("tipo", "herramienta");
                } else if (producto instanceof ProductoElectrodomestico) {
                    ProductoElectrodomestico productoElectrodomestico = (ProductoElectrodomestico) producto;
                    docProducto.append("tipo producto", productoElectrodomestico.getTipoProducto())
                            .append("tipo", "electrodomestico");
                } else if (producto instanceof ProductoAccesorio) {
                    ProductoAccesorio productoAccesorio = (ProductoAccesorio) producto;
                    docProducto.append("categoria", productoAccesorio.getCategoria())
                            .append("tipo", "accesorio");
                } else if (producto instanceof ProductoUtensilioCocina) {
                    ProductoUtensilioCocina productoUtensilioCocina = (ProductoUtensilioCocina) producto;
                    docProducto.append("material", productoUtensilioCocina.getMaterial())
                            .append("uso", productoUtensilioCocina.getUso())
                            .append("tipo", "utensilio_cocina");
                }
                // Agregar mÃ¡s condiciones para otros tipos de productos...

                // Insertar el documento en la colecciÃ³n
                collection.insertOne(docProducto);

                System.out.println("Producto '" + producto.getNombre() + "' aÃ±adido correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al aÃ±adir el producto en la base de datos MongoDB.");
            } finally {
                // Cerrar la conexiÃ³n al finalizar
                ConexionMongoDB.cerrarConexion();
            }
        }
    }
    
    
    
    //METODOS PARA BUSCAR PRODUCTOS EN LA BBDD
    
    //1. BUSCAR POR NOMBRE
    
    public static void buscarPorNombre(String nombre) {
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();

        if (ConexionMongoDB.isConnected()) {
            try {
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");
                MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

                // Utilizar una expresiÃ³n regular para buscar el nombre
                Pattern regex = Pattern.compile(nombre, Pattern.CASE_INSENSITIVE); // Ignorar mayÃºsculas y minÃºsculas
                FindIterable<Document> iterable = collection.find(Filters.regex("nombre", regex));

                imprimirResultado(iterable);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al buscar productos por nombre en la base de datos MongoDB.");
            } finally {
                ConexionMongoDB.cerrarConexion();
            }
        }
    }

    //2. BUSCAR POR MARCA
    
    public static void buscarPorMarca(String marca) {
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();

        if (ConexionMongoDB.isConnected()) {
            try {
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");
                MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

                // Utilizar una expresiÃ³n regular para buscar la marca
                Pattern regex = Pattern.compile(marca, Pattern.CASE_INSENSITIVE); // Ignorar mayÃºsculas y minÃºsculas
                FindIterable<Document> iterable = collection.find(Filters.regex("marca", regex));

                imprimirResultado(iterable);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al buscar productos por marca en la base de datos MongoDB.");
            } finally {
                ConexionMongoDB.cerrarConexion();
            }
        }
    }

    //3. BUSCAR POR CANTIDAD MAYOR QUE

    public static void buscarPorCantidadMayorQue(int cantidad) {
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();

        if (ConexionMongoDB.isConnected()) {
            try {
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");
                MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

                FindIterable<Document> iterable = collection.find(Filters.gt("cantidad", cantidad));

                imprimirResultado(iterable);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al buscar productos por cantidad mayor que en la base de datos MongoDB.");
            } finally {
                ConexionMongoDB.cerrarConexion();
            }
        }
    }

    //4. BUSCAR POR CANTIDAD MENOR QUE
    
    public static void buscarPorCantidadMenorQue(int cantidad) {
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();

        if (ConexionMongoDB.isConnected()) {
            try {
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");
                MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

                FindIterable<Document> iterable = collection.find(Filters.lte("cantidad", cantidad));

                imprimirResultado(iterable);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al buscar productos por cantidad menor que en la base de datos MongoDB.");
            } finally {
                ConexionMongoDB.cerrarConexion();
            }
        }
    }

    //5. BUSCAR POR PRECIO MAYOR QUE

    public static void buscarPorPrecioMayorQue(int precio) {
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();

        if (ConexionMongoDB.isConnected()) {
            try {
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");
                MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

                FindIterable<Document> iterable = collection.find(Filters.gte("precio", precio));

                imprimirResultado(iterable);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al buscar productos por cantidad mayor que en la base de datos MongoDB.");
            } finally {
                ConexionMongoDB.cerrarConexion();
            }
        }
    }
    
    //5. BUSCAR POR PRECIO MENOR QUE

    public static void buscarPorPrecioMenorQue(int precio) {
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();

        if (ConexionMongoDB.isConnected()) {
            try {
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");
                MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

                FindIterable<Document> iterable = collection.find(Filters.lte("precio", precio));

                imprimirResultado(iterable);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al buscar productos por cantidad mayor que en la base de datos MongoDB.");
            } finally {
                ConexionMongoDB.cerrarConexion();
            }
        }
    }
    
    public static void mostrarTodosLosProductos() {
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();

        if (ConexionMongoDB.isConnected()) {
            try {
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");
                MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

             // Obtener todos los documentos de la colección
                FindIterable<Document> iterable = collection.find();

                // Imprimir los resultados de forma legible
                imprimirResultado(iterable);

                

            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al mostrar todos los productos en la base de datos MongoDB.");
            } finally {
                ConexionMongoDB.cerrarConexion();
            }
        }
    }

    
    
    //METODO PARA IMPRIMIR RESULTADO
    
    private static void imprimirResultado(FindIterable<Document> iterable) {
		System.out.println("Resultados de la bÃºsqueda:");

		try (MongoCursor<Document> cursor = iterable.iterator()) {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				// Usamos pretty() para formatear el documento antes de imprimirlo
				System.out.println(pretty(doc.toJson()));
			}
		}
	}

	// MÃ©todo para formatear un documento JSON utilizando pretty()
	private static String pretty(String json) {
		JsonElement je = JsonParser.parseString(json);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(je);
	}
    // Otros mÃ©todos para eliminar, editar y buscar productos...
    
//METODOS PARA ELIMINAR PRODUCTOS DE LA BBDD
    
    //FILTRA VARIOS PRODUCTOS POR NOMBRE Y PIDE UN NOMBRE EXACTO DEL PRODUCTO PARA ELIMINARLO
    
    public static void eliminarPorNombre(String nombre) {
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();

        if (ConexionMongoDB.isConnected()) {
            try {
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");
                MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

                // Utilizar una expresión regular para buscar el nombre
                Pattern regex = Pattern.compile(nombre, Pattern.CASE_INSENSITIVE); // Ignorar mayúsculas y minúsculas
                FindIterable<Document> iterable = collection.find(Filters.regex("nombre", regex));

                // Imprimir los resultados de la búsqueda
                imprimirResultado(iterable);

                // Preguntar al usuario si desea eliminar algún producto mostrado
                Scanner scanner = new Scanner(System.in);
                System.out.println("¿Desea eliminar alguno de los productos mostrados? (S/N)");
                String respuesta = scanner.nextLine().trim().toUpperCase();

                if (respuesta.equals("S")) {
                    // Pedir al usuario que ingrese el nombre exacto del producto a eliminar
                    System.out.print("Ingrese el nombre exacto del producto a eliminar: ");
                    String nombreExacto = scanner.nextLine().trim();
                    System.out.println("");

                    // Eliminar el producto por su nombre exacto
                    collection.deleteMany(Filters.eq("nombre", nombreExacto));
                    System.out.println("Producto(s) eliminado(s) correctamente.");
                    System.out.println("");
                } else {
                    System.out.println("No se ha eliminado ningún producto.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al buscar y eliminar productos por nombre en la base de datos MongoDB.");
            } finally {
                ConexionMongoDB.cerrarConexion();
            }
        }
    }
    
    //FILTRA VARIOS PRODUCTOS POR UNA MISMA MARCA Y PIDE EL NOMBRE EXACTO DEL PRODUCTO PARA ELIMINARLO
    
    public static void eliminarPorMarca(String marca) {
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();

        if (ConexionMongoDB.isConnected()) {
            try {
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");
                MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

                // Utilizar una expresión regular para buscar la marca
                Pattern regex = Pattern.compile(marca, Pattern.CASE_INSENSITIVE); // Ignorar mayúsculas y minúsculas
                FindIterable<Document> iterable = collection.find(Filters.regex("marca", regex));

                // Imprimir los resultados de la búsqueda
                imprimirResultado(iterable);

                // Preguntar al usuario si desea eliminar algún producto mostrado
                Scanner scanner = new Scanner(System.in);
                System.out.println("¿Desea eliminar alguno de los productos mostrados? (S/N)");
                String respuesta = scanner.nextLine().trim().toUpperCase();

                if (respuesta.equals("S")) {
                    // Pedir al usuario que ingrese el nombre exacto del producto a eliminar
                    System.out.print("Ingrese el nombre exacto del producto a eliminar: ");
                    String nombreExacto = scanner.nextLine().trim();

                    // Eliminar el producto por su nombre exacto
                    collection.deleteMany(Filters.eq("nombre", nombreExacto));
                    System.out.println("Producto(s) eliminado(s) correctamente.");
                } else {
                    System.out.println("No se ha eliminado ningún producto.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al buscar y eliminar productos por marca en la base de datos MongoDB.");
            } finally {
                ConexionMongoDB.cerrarConexion();
            }
        }
    }

    /*
     * FILTRA LOS PRODUCTOS POR UNA VARIABLE CANTIDAD INTRODUCIDA PREVIAMENTE,
     * MUESTRA LOS PRODUCTOS POR UNA CANTIDAD IGUAL O MAYOR A ESTA
     * Y PIDE EL NOMBRE EXACTO DEL PRODUCTO PARA ELIMINARLO
     */
    
    public static void eliminarPorCantidadMayorQue(int cantidad) {
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();

        if (ConexionMongoDB.isConnected()) {
            try {
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");
                MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

                // Utilizar un filtro para buscar productos con cantidad mayor que la especificada
                Bson filter = Filters.gt("cantidad", cantidad);
                FindIterable<Document> iterable = collection.find(filter);

                // Imprimir los productos encontrados
                imprimirResultado(iterable);

                // Solicitar al usuario que ingrese el nombre del producto a eliminar
                Scanner scanner = new Scanner(System.in);
                System.out.println("Introduce el nombre del producto a eliminar:");
                String nombreProducto = scanner.nextLine();

                // Eliminar el producto por nombre
                eliminarPorNombre(nombreProducto);

            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al buscar productos por cantidad en la base de datos MongoDB.");
            } finally {
                ConexionMongoDB.cerrarConexion();
            }
        }
    }
    
    /*
     * FILTRA LOS PRODUCTOS POR UNA VARIABLE CANTIDAD INTRODUCIDA PREVIAMENTE,
     * MUESTRA LOS PRODUCTOS POR UNA CANTIDAD IGUAL O MENOR A ESTA
     * Y PIDE EL NOMBRE EXACTO DEL PRODUCTO PARA ELIMINARLO
     */
    
    //ELIMINAR POR CANTIDAD MENOR QUE
    
    public static void eliminarPorCantidadMenorQue(int cantidad) {
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();

        if (ConexionMongoDB.isConnected()) {
            try {
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");
                MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

                // Utilizar un filtro para buscar productos con cantidad menor que la especificada
                Bson filter = Filters.lt("cantidad", cantidad);
                FindIterable<Document> iterable = collection.find(filter);

                // Imprimir los productos encontrados
                imprimirResultado(iterable);

                // Solicitar al usuario que ingrese el nombre del producto a eliminar
                Scanner scanner = new Scanner(System.in);
                System.out.println("Introduce el nombre del producto a eliminar:");
                String nombreProducto = scanner.nextLine();

                // Eliminar el producto por nombre
                eliminarPorNombre(nombreProducto);

            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al buscar productos por cantidad en la base de datos MongoDB.");
            } finally {
                ConexionMongoDB.cerrarConexion();
            }
        }
    }
   
    /*
     * FILTRA LOS PRODUCTOS POR UNA VARIABLE PRECIO INTRODUCIDA PREVIAMENTE,
     * MUESTRA LOS PRODUCTOS POR UNA CANTIDAD IGUAL O MAYOR A ESTA
     * Y PIDE EL NOMBRE EXACTO DEL PRODUCTO PARA ELIMINARLO
     */
    
    public static void eliminarPorPrecioMayorQue(int precio) {
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();

        if (ConexionMongoDB.isConnected()) {
            try {
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");
                MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

                // Utilizar un filtro para buscar productos con cantidad mayor que la especificada
                Bson filter = Filters.gte("precio", precio);
                FindIterable<Document> iterable = collection.find(filter);

                // Imprimir los productos encontrados
                imprimirResultado(iterable);

                // Solicitar al usuario que ingrese el nombre del producto a eliminar
                Scanner input = new Scanner(System.in);
                System.out.println("Introduce el nombre del producto a eliminar:");
                String nombreProducto = input.nextLine();

                // Eliminar el producto por nombre
                eliminarPorNombre(nombreProducto);

            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al buscar productos por cantidad en la base de datos MongoDB.");
            } finally {
                ConexionMongoDB.cerrarConexion();
            }
        }
    }

    /*
     * FILTRA LOS PRODUCTOS POR UNA VARIABLE PRECIO INTRODUCIDA PREVIAMENTE,
     * MUESTRA LOS PRODUCTOS POR UNA CANTIDAD IGUAL O MENOR A ESTA
     * Y PIDE EL NOMBRE EXACTO DEL PRODUCTO PARA ELIMINARLO
     */
    
    // Método para modificar el nombre de un producto 
    public static void eliminarPorPrecioMenorQue(int precio) {
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();

        if (ConexionMongoDB.isConnected()) {
            try {
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");
                MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

                // Utilizar un filtro para buscar productos con precio menor o igual al especificado
                Bson filter = Filters.lte("precio", precio);
                FindIterable<Document> iterable = collection.find(filter);

                // Imprimir los productos encontrados
                imprimirResultado(iterable);

                // Solicitar al usuario que ingrese el nombre del producto a eliminar
                Scanner input = new Scanner(System.in);
                System.out.println("Introduce el nombre del producto a eliminar:");
                String nombreProducto = input.nextLine();

                // Eliminar el producto por nombre
                eliminarPorNombre(nombreProducto);

            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al buscar productos por cantidad en la base de datos MongoDB.");
            } finally {
                ConexionMongoDB.cerrarConexion();
            }
        }
    }
 
    //FILTRA LOS PRODUCTOS POR SU MARCA Y ELIMINA A TODOS LOS PRODUCTOS CON ESA MISMA VARIABLE MARCA
    
    //ELIMINAR TODOS LOS PRODUCTOS DE UNA MARCA
    public static void eliminarProductosMarca(String marca) {
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();

        if (ConexionMongoDB.isConnected()) {
            try {
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");
                MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

                // Eliminar todos los productos con la marca especificada
                collection.deleteMany(eq("marca", marca));

                System.out.println("Productos de la marca '" + marca + "' eliminados correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al eliminar productos por marca en la base de datos MongoDB.");
            } finally {
                ConexionMongoDB.cerrarConexion();
            }
        }
    }
    
    /*
     * FILTRA LOS PRODUCTOS CON UNA VARIABLE CANTIDAD IGUAL O MAYOR A LA INTRODUCIDA PREVIAMENTE
     * Y LOS ELIMINA
     */
    
    //ELIMINA TODOS LOS PRODUCTOS CON UNA CANTIDAD IGUAL O MAYOR A LA CANTIDAD INDICADA
    public static void eliminarProductosCantidadMayor(int cantidad) {
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();
        
        if (ConexionMongoDB.isConnected()) {
            try {
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");
                MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

                // Eliminar todos los productos con cantidad mayor o igual a la especificada
                collection.deleteMany(Filters.gte("cantidad", cantidad));
                
                System.out.println("Productos con cantidad mayor o igual a " + cantidad + " unidades eliminados correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al eliminar productos por cantidad en la base de datos MongoDB.");
            } finally {
                ConexionMongoDB.cerrarConexion();
            }
        }
    }

    /*
     * FILTRA LOS PRODUCTOS CON UNA VARIABLE CANTIDAD IGUAL O MENOR A LA INTRODUCIDA PREVIAMENTE
     * Y LOS ELIMINA
     */
    
    //ELIMINAR TODOS LOS PRODUCTOS POR CANTIDAD X O MENOS
    
    public static void eliminarProductosCantidadMenor(int cantidad) {
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();
        
        if (ConexionMongoDB.isConnected()) {
            try {
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");
                MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

                // Eliminar todos los productos con cantidad menor o igual a la especificada
                collection.deleteMany(Filters.lte("cantidad", cantidad));
                
                System.out.println("Productos con cantidad menor o igual a " + cantidad + " unidades eliminados correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al eliminar productos por cantidad en la base de datos MongoDB.");
            } finally {
                ConexionMongoDB.cerrarConexion();
            }
        }
    }
    
    /*
     * FILTRA LOS PRODUCTOS CON UNA VARIABLE PRECIO IGUAL O MAYOR A LA INTRODUCIDA PREVIAMENTE
     * Y LOS ELIMINA
     */
    
    public static void eliminarProductosPrecioMayor(double precio) {
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();
        
        if (ConexionMongoDB.isConnected()) {
            try {
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");
                MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

                // Eliminar todos los productos con precio mayor o igual al especificado
                collection.deleteMany(Filters.gte("precio", precio));
                
                System.out.println("Productos con precio mayor o igual a " + precio + " eliminados correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al eliminar productos por precio en la base de datos MongoDB.");
            } finally {
                ConexionMongoDB.cerrarConexion();
            }
        }
    }
    
    /*
     * FILTRA LOS PRODUCTOS CON UNA VARIABLE CANTIDAD IGUAL O MENOR A LA INTRODUCIDA PREVIAMENTE
     * Y LOS ELIMINA
     */
    
    public static void eliminarProductosPrecioMenor(double precio) {
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();
        
        if (ConexionMongoDB.isConnected()) {
            try {
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");
                MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

                // Eliminar todos los productos con precio menor o igual al especificado
                collection.deleteMany(Filters.lte("precio", precio));
                
                System.out.println("Productos con precio menor o igual a " + precio + " eliminados correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al eliminar productos por precio en la base de datos MongoDB.");
            } finally {
                ConexionMongoDB.cerrarConexion();
            }
        }
    }
    
   

    
    
    
    public static void modificarProducto(Scanner scanner) {
	    System.out.println("Ingrese el nombre del producto que desea modificar:");
	    String nombreProducto = scanner.nextLine();

	    MongoClient mongoClient = ConexionMongoDB.getMongoClient();

	    if (ConexionMongoDB.isConnected()) {
	        try {
	            MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");
	            MongoCollection<Document> collection = database.getCollection(NOMBRE_COLECCION);

	            Document producto = collection.find(Filters.eq("nombre", nombreProducto)).first();

	            if (producto != null) {
	                System.out.println("Producto encontrado:");
	                System.out.println(pretty(producto.toJson()));

	                System.out.println("Ingrese el nuevo nombre del producto (deje vacÃ­o si no desea cambiar):");
	                String nuevoNombre = scanner.nextLine();

	                System.out.println("Ingrese el nuevo precio del producto (deje vacÃ­o si no desea cambiar):");
	                String nuevoPrecio = scanner.nextLine();

	                System.out.println("Ingrese la nueva descripciÃ³n del producto (deje vacÃ­o si no desea cambiar):");
	                String nuevaDescripcion = scanner.nextLine();

	                System.out.println("Ingrese la nueva marca del producto (deje vacÃ­o si no desea cambiar):");
	                String nuevaMarca = scanner.nextLine();
	                
	                System.out.println("Ingrese el nuevo peso del producto (deje vacÃ­o si no desea cambiar):");
	                String nuevoPeso = scanner.nextLine();

	                System.out.println("Ingrese la nueva cantidad del producto (deje vacÃ­o si no desea cambiar):");
	                String nuevaCantidad = scanner.nextLine();

	                System.out.println("Ingrese la nueva fecha de caducidad del producto (deje vacÃ­o si no desea cambiar):");
	                String nuevaFechaCaducidad = scanner.nextLine();
	                
	                System.out.println("Â¿Es congelado? (true/false) (deje vacÃ­o si no desea cambiar):");
	                String esCongeladoInput = scanner.nextLine();
	                Boolean nuevoEsCongelado = null;
	                if (!esCongeladoInput.isEmpty()) {
	                    nuevoEsCongelado = Boolean.parseBoolean(esCongeladoInput);
	                }

	                Document updateDocumento = new Document("$set", new Document());

	                if (!nuevoNombre.isEmpty()) {
	                    updateDocumento.get("$set", Document.class).append("nombre", nuevoNombre);
	                }
	                if (!nuevoPrecio.isEmpty()) {
	                    updateDocumento.get("$set", Document.class).append("precio", Double.parseDouble(nuevoPrecio));
	                }
	                if (!nuevaDescripcion.isEmpty()) {
	                    updateDocumento.get("$set", Document.class).append("descripcion", nuevaDescripcion);
	                }
	                if (!nuevaMarca.isEmpty()) {
	                    updateDocumento.get("$set", Document.class).append("marca", nuevaMarca);
	                }
	                
	                if (!nuevoPeso.isEmpty()) {
	                    updateDocumento.get("$set", Document.class).append("peso", Double.parseDouble(nuevoPeso));
	                }
	                if (!nuevaCantidad.isEmpty()) {
	                    updateDocumento.get("$set", Document.class).append("cantidad", Integer.parseInt(nuevaCantidad));
	                }
	                if (!nuevaFechaCaducidad.isEmpty()) {
	                    updateDocumento.get("$set", Document.class).append("fechaCaducidad", nuevaFechaCaducidad);
	                }
	                if (nuevoEsCongelado != null) {
	                    updateDocumento.get("$set", Document.class).append("esCongelado", nuevoEsCongelado);
	                }
	                

	                collection.updateOne(Filters.eq("nombre", nombreProducto), updateDocumento);

	                System.out.println("Producto modificado exitosamente.");
	            } else {
	                System.out.println("Producto no encontrado.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.err.println("Error al modificar el producto en la base de datos MongoDB.");
	        } finally {
	            ConexionMongoDB.cerrarConexion();
	        }
	    }
	}
    //METODOS PARA MODIFICAR LOS ATRIBUTOS DE LOS DISTINTOS PRODUCTOS
    
    // MÃ©todo para modificar el nombre de un producto
    


}
