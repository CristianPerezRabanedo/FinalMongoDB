package MongoDB;
import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class ConexionMongoDB {

    private static MongoClient mongoClient;

    private ConexionMongoDB() {
    }

    public static MongoClient getMongoClient() {
        if (mongoClient == null) {
            try {
                // Reemplaza '<password>' con tu contraseña real
                String connectionString = "mongodb+srv://usuario:<password>@cluster0.4cwyc7y.mongodb.net/";
                mongoClient = MongoClients.create(connectionString);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al conectar a la base de datos MongoDB.");
            }
        }
        return mongoClient;
    }

    public static boolean isConnected() {
        if (mongoClient != null) {
            try {
                // Obtener la versión de la base de datos
                String version = mongoClient.getDatabase("admin").runCommand(new Document("buildInfo", 1)).getString("version");
                //System.out.println("Conectado a la base de datos MongoDB versión: " + version);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al obtener información de la base de datos MongoDB.");
            }
        } else {
            System.err.println("No se ha establecido una conexión a la base de datos MongoDB.");
        }
        return false;
    }

    public static void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
            //System.out.println("Conexión cerrada correctamente.");
        } else {
            System.err.println("No hay ninguna conexión de MongoDB para cerrar.");
        }
    }
}
