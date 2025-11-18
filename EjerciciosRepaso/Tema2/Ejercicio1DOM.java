package EjerciciosRepaso.Tema2;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejercicio1DOM {
    public static Document creaArbol(String ruta) {
        Document doc = null;
        try {
            DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
            factoria.setIgnoringComments(true);
            DocumentBuilder builder = factoria.newDocumentBuilder();
            doc = builder.parse(ruta);
        } catch (Exception e) {
            System.out.println("Error generando el árbol DOM: " + e.getMessage());
        }
        return doc;
    }

    public static void main(String[] args) {
        Document doc = creaArbol("EjerciciosRepaso\\Tema2\\liga.xml");
        NodeList temporada;        
        temporada = doc.getElementsByTagName("temporada");
        for (int i = 0; i < temporada.getLength(); i++) {
            System.out.printf("Temporada: %s\n", temporada.item(i).getTextContent());
        }

        System.out.println("----------------EJ2----------------------");
        NodeList evento;
        evento = doc.getElementsByTagName("evento");
        System.out.printf("Nº partidos: %d\n",evento.getLength());


        System.out.println("------------------EJ3-------------------");
        NodeList evento3;
        evento3 = doc.getElementsByTagName("evento");
        for (int i = 0; i < evento3.getLength(); i++) {
            if (evento3.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element cadaEvento = (Element)evento3.item(i);
                String fecha = cadaEvento.getElementsByTagName("fecha").item(0).getTextContent();
                String local = cadaEvento.getElementsByTagName("equipolocal").item(0).getTextContent();
                String visitante = cadaEvento.getElementsByTagName("equipovisitante").item(0).getTextContent();
                System.out.printf("Fecha: %s, Local %15s, Visitante: %15s\n",fecha,local,visitante);
            }
        }

        System.out.println("------------------EJ4-------------------");
        NodeList equipo = doc.getElementsByTagName("team");
        int maxGoles = 0;
        for (int i = 0; i < equipo.getLength(); i++) {
            if (equipo.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element team = (Element)equipo.item(i);
                String goles = team.getElementsByTagName("goals_scored").item(0).getTextContent();
                int goals = Integer.parseInt(goles);

            }
        }
    }
}
