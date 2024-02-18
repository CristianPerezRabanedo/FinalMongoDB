package MongoDB;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class CrearTablaPrueba {

    public static void crearTablaPrueba() {
        // Obtener el cliente de MongoDB desde la clase de conexión
        MongoClient mongoClient = ConexionMongoDB.getMongoClient();

        // Verificar si la conexión está establecida
        if (ConexionMongoDB.isConnected()) {
            try {
                // Obtener la base de datos
                MongoDatabase database = mongoClient.getDatabase("nombre_de_tu_base_de_datos");

                // Verificar si la tabla ya existe
                MongoIterable<String> collectionNames = database.listCollectionNames();
                boolean existeTabla = false;
                for (String name : collectionNames) {
                    if (name.equals("prueba3")) {
                        existeTabla = true;
                        break;
                    }
                }

                if (existeTabla) {
                   // System.out.println("La tabla 'prueba3' ya existe en la base de datos.");
                } else {
                    // Crear una nueva colección (tabla)
                    database.createCollection("prueba3");
                    //System.out.println("Tabla 'prueba3' creada correctamente.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al crear o verificar la tabla en la base de datos MongoDB.");
            } finally {
                // Cerrar la conexión al finalizar
                ConexionMongoDB.cerrarConexion();
            }
        }
    }
}