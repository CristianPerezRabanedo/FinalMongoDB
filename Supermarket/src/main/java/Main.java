import com.mongodb.client.MongoClient;

import Menus.MenuPrincipal;
import MongoDB.ConexionMongoDB;
import MongoDB.CrearTablaPrueba;

public class Main {

    public static void main(String[] args) {
        // Conectar a la base de datos
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();

        // Comprobar la conexión
        if (ConexionMongoDB.isConnected()) {
            System.out.println("¡Conectado!");
        } else {
            System.out.println("Error al conectar a la base de datos.");
            return;
        }

        // Crear la tabla "prueba"
        CrearTablaPrueba.crearTablaPrueba();
        
        // Mostrar el menú principal
        MenuPrincipal.mostrarMenu();

        // Cerrar la conexión
        ConexionMongoDB.cerrarConexion();
    }
}
