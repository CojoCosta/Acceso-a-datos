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

    public static void mostrarTemporada(Document doc) {
        NodeList temporada = doc.getElementsByTagName("temporada");
        for (int i = 0; i < temporada.getLength(); i++) {
            System.out.println(temporada.item(i).getTextContent());
        }
    }

    public static void numPartidos(Document doc) {
        NodeList evento = doc.getElementsByTagName("evento");
        System.out.println(evento.getLength());
    }

    public static void equiposFechas(Document doc) {
        NodeList listaEventos = doc.getElementsByTagName("evento");
        System.out.println("PARTIDOS ENCONTRADOS:\n");
        for (int i = 0; i < listaEventos.getLength(); i++) {
            Node nodoEvento = listaEventos.item(i);
            if (nodoEvento.getNodeType() == Node.ELEMENT_NODE) {
                Element evento = (Element) nodoEvento;
                String fecha = evento.getElementsByTagName("fecha").item(0).getTextContent();
                String local = evento.getElementsByTagName("equipolocal").item(0).getTextContent();
                String visitante = evento.getElementsByTagName("equipovisitante").item(0).getTextContent();
                System.out.println(fecha + " — " + local + " vs " + visitante);
            }
        }
    }

    public static void masGoles(Document doc) {
        NodeList listaTeams = doc.getElementsByTagName("team");
        System.out.println("EQUIPO MAXIMO GOLEADOR");
        int golEquipo = 0;
        int maxGoles = 0;
        String equipo = "";
        String maxEquipo = "";
        for (int i = 0; i < listaTeams.getLength(); i++) {
            Node nodoTeam = listaTeams.item(i);
            if (nodoTeam.getNodeType() == Node.ELEMENT_NODE) {
                Element team = (Element) nodoTeam;
                golEquipo = Integer.parseInt(team.getElementsByTagName("goals_scored").item(0).getTextContent());
                equipo = team.getElementsByTagName("name").item(0).getTextContent();
                if (golEquipo > maxGoles) {
                    maxGoles = golEquipo;
                    maxEquipo = equipo;
                }
            }
        }
        System.out.println(maxEquipo + " - " + maxGoles);
    }

    public static void partidoColista(Document doc) {
        NodeList listaTeams = doc.getElementsByTagName("team");
        Element elementoColista = (Element) listaTeams.item(listaTeams.getLength() - 1);
        String nombreColista = elementoColista.getElementsByTagName("name").item(0).getTextContent();
        System.out.println("Nombre del colista:" + nombreColista);

        NodeList listaEventos = doc.getElementsByTagName("evento");
        for (int i = 0; i < listaEventos.getLength(); i++) {
            Element evento = (Element) listaEventos.item(i);

            String local = evento.getElementsByTagName("equipolocal").item(0).getTextContent();
            String visitante = evento.getElementsByTagName("equipovisitante").item(0).getTextContent();
            if (nombreColista.equals(local) || nombreColista.equals(visitante)) {
                String fecha = evento.getElementsByTagName("fecha").item(0).getTextContent();
                String golesLocal = evento.getElementsByTagName("resultadolocal").item(0).getTextContent();
                String golesVisit = evento.getElementsByTagName("resultadovisitante").item(0).getTextContent();
                System.out.println("PARTIDO DEL COLISTA:");
                System.out.println(fecha + " — " + local + " vs " + visitante);
                System.out.println("Resultado: " + golesLocal + " - " + golesVisit);
            }
        }
    }

    public static void masEmpates(Document doc) {
        NodeList listaTeams = doc.getElementsByTagName("team");
        System.out.println("EQUIPO MAXIMO EMPATADOR");
        int empate = 0;
        int empateMax = 0;
        String equipo = "";
        String equipoMax = "";
        for (int i = 0; i < listaTeams.getLength(); i++) {
            Element cadaEquipo = (Element) listaTeams.item(i);
            empate = Integer.parseInt(cadaEquipo.getElementsByTagName("drawn").item(0).getTextContent());
            equipo = cadaEquipo.getElementsByTagName("name").item(0).getTextContent();
            if (empate > empateMax) {
                empateMax = empate;
                equipoMax = equipo;
            }
        }
        System.out.printf("El equipo %s ha sido el maximo empatador con %d empates\n", equipoMax, empateMax);
    }

    public static void clasifTercerPartido(Document doc) {
        NodeList eventos = doc.getElementsByTagName("evento");
        Element tercerPartido = (Element) eventos.item(2);
        String equipoLocal = tercerPartido.getElementsByTagName("equipolocal").item(0).getTextContent();
        String equipoVisitante = tercerPartido.getElementsByTagName("equipovisitante").item(0).getTextContent();
        String rangoLocal = "";
        String rangoVisitante = "";
        NodeList equipos = doc.getElementsByTagName("team");
        for (int i = 0; i < equipos.getLength(); i++) {
            Element cadaEquipo = (Element)equipos.item(i);
            String nombreEquipo = cadaEquipo.getElementsByTagName("name").item(0).getTextContent();
            if (nombreEquipo.equals(equipoLocal)){
                rangoLocal = cadaEquipo.getElementsByTagName("rank").item(0).getTextContent();
            } else if (nombreEquipo.equals(equipoVisitante)) {
                rangoVisitante = cadaEquipo.getElementsByTagName("rank").item(0).getTextContent();
            }
        }
        System.out.printf("Equipo local %s, posicion %s\n", equipoLocal, rangoLocal);
        System.out.printf("Equipo visitante %s, posicion %s\n", equipoVisitante, rangoVisitante);
    }

    public static void main(String[] args) {
        Document doc = creaArbol("EjerciciosRepaso\\Tema2\\liga.xml");
        // Ejercicio 1
        mostrarTemporada(doc);
        System.out.println();

        // Ejercicio 2
        numPartidos(doc);
        System.out.println();

        // Ejercicio 3
        equiposFechas(doc);
        System.out.println();

        // Ejercicio 4
        masGoles(doc);
        System.out.println();
        
        // Ejercicio 5
        partidoColista(doc);
        System.out.println();
        
        // Ejercicio 6
        masEmpates(doc);
        System.out.println();
        
        // Ejercicio 7
        clasifTercerPartido(doc);
        System.out.println();
    }

}
