package json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.net.ssl.HttpsURLConnection;

public class Application {
  public static JsonValue leeJSON(String ruta) {
    try {
      if (ruta.toLowerCase().startsWith("http://")) {
        return leerHttp(ruta);
      } else if (ruta.toLowerCase().startsWith("https://")) {
        return leerHttps(ruta);
      } else {
        return leerFichero(ruta);
      }
    } catch (IOException e) {
      System.out.println("Error procesando documento Json " +
          e.getLocalizedMessage());
      return null;
    }
  }

  public static JsonValue leerFichero(String ruta) throws FileNotFoundException {
    try (JsonReader reader = Json.createReader(new FileReader(ruta))) {
      return reader.read();
      /*
       * JsonStructure jsonSt = reader.read();
       * System.out.println(jsonSt.getValueType());
       * JsonObject jsonObj = reader.readObject();
       * System.out.println(jsonObj.getValueType());
       * JsonArray jsonArr = reader.readArray();
       * System.out.println(jsonArr.getValueType());
       */
    }
  }

  public static JsonValue leerHttp(String direccion) throws IOException {
    URL url = new URL(direccion);
    try (InputStream is = url.openStream();
        JsonReader reader = Json.createReader(is)) {
      return reader.read();
    }
  }

  public static JsonValue leerHttps(String direccion) throws IOException {
    URL url = new URL(direccion);
    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
    try (InputStream is = conn.getInputStream();
        JsonReader reader = Json.createReader(is)) {
      return reader.read();
    } finally {
      conn.disconnect();
    }
  }

  public static void escribeJSON(JsonValue json, File f) throws FileNotFoundException {
    System.out.println("Guardando tipo: " + json.getValueType());
    PrintWriter pw = new PrintWriter(f);
    JsonWriter writer = Json.createWriter(pw);
    // writer.write((JsonStructure) json);
    if (json.getValueType() == JsonValue.ValueType.OBJECT) {
      writer.writeObject(json.asJsonObject());
      // writer.writeObject((JsonObject)json);
    } else if (json.getValueType() == JsonValue.ValueType.ARRAY) {
      writer.writeArray(json.asJsonArray());
      // writer.writeArray((JsonArray)json);
    } else
      System.out.println("No se soporta la escritura");
      writer.close();
  }
  public static void navegarPelis(){
    JsonValue jv = leeJSON("src\\main\\java\\Resources\\pelis.json");
    System.out.println(jv);
    JsonArray ja = jv.asJsonArray();
    for (JsonValue peli : ja) {
      JsonObject p = peli.asJsonObject();
      System.out.printf("+ Titulo: %s \n- Año: %d\n",p.getString("titulo"), p.getInt("año"));
      JsonArray interpretes = p.getJsonArray("interpretes");
      System.out.println("Interpretes");
      for (JsonValue interprete : interpretes) {
        JsonObject inter = interprete.asJsonObject();
        System.out.printf("+ Nombre: %s \n",inter.getString("nombre"));
        System.out.printf("Año: %d \nMes: %d\n", inter.getJsonObject("fechaNacimiento").getInt("año"), inter.getJsonObject("fechaNacimiento").getInt("mes"));
      }

    }
  }

  public static void main(String[] args) throws FileNotFoundException {
    // System.out.println(leeJSON("https://native-stats.org/competition/PD/"));
    // JsonValue json = leeJSON("https://pokeapi.co/api/v2/pokemon/ditto");
    // escribeJSON(json, new File("src\\main\\java\\Resources\\ditto.json"));
    navegarPelis();
  }
}
