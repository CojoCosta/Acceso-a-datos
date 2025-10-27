package ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.net.ssl.HttpsURLConnection;

import netscape.javascript.JSObject;

public class Main {
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

  public static JsonValue ejercicio1() {
    String ciudad = "ourense";
    JsonValue j = leeJSON("https://api.openweathermap.org/data/2.5/weather?q=" + ciudad
        + ",es&lang=es&+units=metric&APPID=8f8dccaf02657071004202f05c1fdce0");
    return j;
  }

  public static JsonValue ejercicio2(double lat, double lon) {
    JsonValue j = leeJSON("https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon
        + "&APPID=8f8dccaf02657071004202f05c1fdce0");
    return j;
  }

  public static JsonValue ejercicio3(double lat, double lon, int x) {
    JsonValue j = leeJSON("http://api.openweathermap.org/data/2.5/find?lat=" + lat + "&lon=" + lon + "&cnt=" + x
        + "&APPID=a975f935caf274ab016f4308ffa23453");
    return j;
  }

  public static int ejercicio4Id(JsonObject jo) {
    int id;
    id = jo.getInt("id");
    return id;
  }

  public static String ejercicio5Nombre(JsonObject jo) {
    String nombre;
    nombre = jo.getString("name");
    return nombre;
  }

  public static double[] ejercicio6Coordenadas(JsonObject jo) {
    JsonObject coord = jo.getJsonObject("coord");
    double lon = coord.getJsonNumber("lon").doubleValue();
    double lat = coord.getJsonNumber("lat").doubleValue();
    double[] coordenadas = { lon, lat };
    return coordenadas;
  }

  public static String unixTimeToString(long unixTime) {
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return Instant.ofEpochSecond(unixTime).atZone(ZoneId.of("GMT+1")).format(formatter);
  }

  public static String ejercicio7(JsonObject jo) {
    //Fecha
    long fecha = jo.getInt("dt");
    //Tª
    JsonObject main = jo.getJsonObject("main");
    double temp = main.getJsonNumber("temp").doubleValue();
    //Humdedad
    int humedad = main.getInt("humidity");
    //PROBABILIDAD CIELO NUBES
    JsonObject nubes = jo.getJsonObject("clouds");
    int prob_nubes = nubes.getInt("all");
    //VELOCIDAD DEL VIENTO
    JsonObject viento = jo.getJsonObject("wind");
    double velocidad = viento.getJsonNumber("speed").doubleValue();
    //PRONOSTICO
    JsonArray tiempo = jo.getJsonArray("weather");
    JsonObject pronostico = tiempo.getJsonObject(0);
    String pronosticoTiempo = pronostico.getString("description");

    return String.format("Fecha: %s\nTª: %f\nHumedad: %d\nPro. cielo nubes: %d\nVel. viento: %f\npronostico: %s", unixTimeToString(fecha), temp, humedad, prob_nubes, velocidad, pronosticoTiempo);
  }

  public static void main(String[] args) throws FileNotFoundException {
    JsonValue j1, j2, j3;

    System.out.println("--------------------Ejercicio 1--------------------------");
    j1 = ejercicio1();
    System.out.println(j1);

    System.out.println("--------------------Ejercicio 2--------------------------");
    j2 = ejercicio2(42.232819, -8.72264);
    System.out.println(j2);

    System.out.println("--------------------Ejercicio 3--------------------------");
    j3 = ejercicio3(42.232819, -8.72264, 1);
    System.out.println(j3);

    JsonObject jo1 = j1.asJsonObject();
    System.out.println("--------------------Ejercicio 4--------------------------");
    System.out.printf("Id es: %d\n", ejercicio4Id(jo1));

    System.out.println("--------------------Ejercicio 5--------------------------");
    System.out.printf("Id es: %s\n", ejercicio5Nombre(jo1));

    System.out.println("--------------------Ejercicio 6--------------------------");
    ejercicio6Coordenadas(jo1);
    double lon = ejercicio6Coordenadas(jo1)[0];
    double lat = ejercicio6Coordenadas(jo1)[1];
    System.out.printf("Coordenadas lon: %f, lat %f\n", lon, lat);

    System.out.println("--------------------Ejercicio 7--------------------------");
    System.out.println(ejercicio7(jo1));
    
    System.out.println("--------------------Ejercicio 8--------------------------");
    
  }
}
