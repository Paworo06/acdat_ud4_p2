import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Main {
    static void main() {

        //Cadena de conexión con la BBDD
        String uri = "mongodb+srv://paworo06:uTa6FNYP3riMZ4Ah@mongodb.5zftvyh.mongodb.net/?retryWrites=true&w=majority";

        //Creación del cliente
        try (MongoClient client = MongoClients.create(uri)) {

            System.out.println("Creando conexión con la Base de datos");

            //Obtenemos la BBDD apor su nombre
            MongoDatabase db = client.getDatabase("MongoDB");

            //Y obtenemos la colección
            MongoCollection<Document> col = db.getCollection("tutorial");

            System.out.println("Realizando consulta previa a la inserción del documento");

            //Consulta previa a la inserción de un nuevo documento
            if (col.countDocuments() == 0) {
                System.out.println("No se encuentran documentos en la BBDD");
            } else {
                for (Document encontrado : col.find()) {
                    System.out.println(encontrado.toJson());
                }
            }


            //Creación del documento
            Document doc = new Document("titulo", "2")
                    .append("anyo", 2026)
                    .append("completado", true);

            System.out.println("\nInsertando nuevo documento");

            //Insertamos el documento en la colección
            if (col.insertOne(doc).wasAcknowledged()) {
                System.out.println("\nNuevo documento insertado correctamente");
            };

            //Consulta básica
            System.out.println("Documentos encontrados en la BBDD");
            for (Document encontrado : col.find()) {
                System.out.println(encontrado.toJson());
            }

        } catch (Exception e) {
            System.out.println("Error en la conexión o inserción");
        }


    }
}
