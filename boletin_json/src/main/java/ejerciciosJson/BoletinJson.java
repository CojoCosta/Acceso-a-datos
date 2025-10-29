package ejerciciosJson;

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
import javax.print.DocFlavor.SERVICE_FORMATTED;

public class BoletinJson {
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
    return leeJSON("https://api.openweathermap.org/data/2.5/weather?q=" + ciudad
        + ",es&lang=es&+units=metric&APPID=8f8dccaf02657071004202f05c1fdce0");
  }

  public static JsonValue ejercicio2(double lat, double lon) {
    return leeJSON("https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon
        + "&APPID=8f8dccaf02657071004202f05c1fdce0");
  }

  public static JsonValue ejercicio3(double lat, double lon, int x) {
    return leeJSON("http://api.openweathermap.org/data/2.5/find?lat=" + lat + "&lon=" + lon + "&cnt=" + x
        + "&APPID=a975f935caf274ab016f4308ffa23453");
  }

  public static JsonValue ejercicio9JsonValue() {
    return leeJSON("https://opentdb.com/api.php?amount=20&category=9&difficulty=hard&type=multiple");
  }

  public static JsonValue ejercicio10JsonValue(String pais) {
    return leeJSON("https://app.ticketmaster.com/discovery/v2/events.json?countryCode=" + pais
        + "&apikey=AMXR5Rf8zlr7oGucsebGKvDCLOQmGUGE");
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
    // Fecha
    long fecha = jo.getInt("dt");
    // Tª
    JsonObject main = jo.getJsonObject("main");
    double temp = main.getJsonNumber("temp").doubleValue();
    // Humdedad
    int humedad = main.getInt("humidity");
    // PROBABILIDAD CIELO NUBES
    JsonObject nubes = jo.getJsonObject("clouds");
    int prob_nubes = nubes.getInt("all");
    // VELOCIDAD DEL VIENTO
    JsonObject viento = jo.getJsonObject("wind");
    double velocidad = viento.getJsonNumber("speed").doubleValue();
    // PRONOSTICO
    JsonArray tiempo = jo.getJsonArray("weather");
    JsonObject pronostico = tiempo.getJsonObject(0);
    String pronosticoTiempo = pronostico.getString("description");

    return String.format("Fecha: %s\nTª: %f\nHumedad: %d\nPro. cielo nubes: %d\nVel. viento: %f\npronostico: %s\n\n",
        unixTimeToString(fecha), temp, humedad, prob_nubes, velocidad, pronosticoTiempo);
  }

  public static void ejercicio8(JsonObject jo) {
    JsonArray list = jo.getJsonArray("list");

    for (int k = 0; k < list.size(); k++) {
      JsonObject objetoList = list.getJsonObject(k);
      // FECHA
      long fecha = objetoList.getInt("dt");
      // Tª
      JsonObject main = objetoList.getJsonObject("main");
      double temp = main.getJsonNumber("temp").doubleValue();
      // Humdedad
      int humedad = main.getInt("humidity");
      // PROBABILIDAD CIELO NUBES
      JsonObject nubes = objetoList.getJsonObject("clouds");
      int prob_nubes = nubes.getInt("all");
      // VELOCIDAD DEL VIENTO
      JsonObject viento = objetoList.getJsonObject("wind");
      double velocidad = viento.getJsonNumber("speed").doubleValue();
      // PRONOSTICO
      JsonArray tiempo = objetoList.getJsonArray("weather");
      JsonObject pronostico = tiempo.getJsonObject(0);
      String pronosticoTiempo = pronostico.getString("description");
      System.out.printf("Fecha: %s\nTª: %f\nHumedad: %d\nPro. cielo nubes: %d\nVel. viento: %f\npronostico: %s\n\n",
          unixTimeToString(fecha), temp, humedad, prob_nubes, velocidad, pronosticoTiempo);
    }
  }

  public static void ejercicio9(JsonObject jo) {
    JsonArray results = jo.getJsonArray("results");
    for (int i = 0; i < results.size(); i++) {
      JsonObject resultObject = results.getJsonObject(i);
      // PREGUNTA
      String pregunta = resultObject.getString("question");
      // RESPUEST CORRECTA
      String respuestaCorrecta = resultObject.getString("correct_answer");
      // RESPUESTAS INCORRECTAS
      JsonArray respuestasMalas = resultObject.getJsonArray("incorrect_answers");
      String respuestaIncorrecta1 = respuestasMalas.getString(0);
      String respuestaIncorrecta2 = respuestasMalas.getString(1);
      String respuestaIncorrecta3 = respuestasMalas.getString(2);
      System.out.printf("Pregunta: %s\n%s*\n%s\n%s\n%s\n\n", pregunta, respuestaCorrecta, respuestaIncorrecta1,
          respuestaIncorrecta2, respuestaIncorrecta3);
    }
  }

  public static void ejercicio10(JsonObject jo) {
    JsonObject embe = jo.getJsonObject("_embedded");
    JsonArray events = embe.getJsonArray("events");

    for (int i = 0; i < events.size(); i++) {
      JsonObject evento = events.getJsonObject(i);
      String nombre = evento.getString("name");
      System.out.printf("Evento %d: %s\n", i, nombre);
    }
  }

  public static void ejercicio11(JsonObject jo) {
    JsonObject embe = jo.getJsonObject("_embedded");
    JsonArray events = embe.getJsonArray("events");

    for (int i = 0; i < events.size(); i++) {
      JsonObject event = events.getJsonObject(i);
      JsonObject embe2 = event.getJsonObject("_embedded");
      JsonArray venues = embe2.getJsonArray("venues");

      for (int j = 0; j < venues.size(); j++) {
        JsonObject lugar = venues.getJsonObject(j);
        String sitio = lugar.getString("name");
        String codPostal = lugar.getString("postalCode");
        JsonObject ciudadJsonObject = lugar.getJsonObject("city");
        String ciudad = ciudadJsonObject.getString("name");
        JsonObject paisJsonObject = lugar.getJsonObject("country");
        String pais = paisJsonObject.getString("name");
        JsonObject direccionJsonObject = lugar.getJsonObject("address");
        String direccion = direccionJsonObject.getString("line1");
        System.out.printf("Lugar: %s \nCodigo Postal: %s \nCiudad: %s \nPaís: %s \nDirección: %s\n\n", sitio, codPostal, ciudad, pais, direccion);
      }
    }
  }

  public static void main(String[] args) throws FileNotFoundException {
    // JsonValue j1, j2, j3;

    // System.out.println("--------------------Ejercicio 1--------------------------");
    // j1 = ejercicio1();
    // System.out.println(j1);

    // System.out.println("--------------------Ejercicio 2--------------------------");
    // j2 = ejercicio2(42.232819, -8.72264);
    // System.out.println(j2);

    // System.out.println("--------------------Ejercicio 3--------------------------");
    // j3 = ejercicio3(42.232819, -8.72264, 5);
    // System.out.println(j3);

    // JsonObject jo1 = j1.asJsonObject();
    // System.out.println("--------------------Ejercicio 4--------------------------");
    // System.out.printf("Id es: %d\n", ejercicio4Id(jo1));

    // System.out.println("--------------------Ejercicio 5--------------------------");
    // System.out.printf("Id es: %s\n", ejercicio5Nombre(jo1));

    // System.out.println("--------------------Ejercicio 6--------------------------");
    // ejercicio6Coordenadas(jo1);
    // double lon = ejercicio6Coordenadas(jo1)[0];
    // double lat = ejercicio6Coordenadas(jo1)[1];
    // System.out.printf("Coordenadas lon: %f, lat %f\n", lon, lat);

    // System.out.println("--------------------Ejercicio 7--------------------------");
    // System.out.println(ejercicio7(jo1));

    // System.out.println("--------------------Ejercicio 8--------------------------");
    // JsonObject jo3 = j3.asJsonObject();
    // ejercicio8(jo3);

    // System.out.println("--------------------Ejercicio 9--------------------------");
    // JsonObject jo9 = ejercicio9JsonValue().asJsonObject();
    // ejercicio9(jo9);

    System.out.println("--------------------Ejercicio 10--------------------------");
    JsonObject jo10 = ejercicio10JsonValue("ES").asJsonObject();
    // System.out.println(jo10);
    ejercicio10(jo10);

    System.out.println("--------------------Ejercicio 11--------------------------");
    ejercicio11(jo10);
  }
}