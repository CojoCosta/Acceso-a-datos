package Boletin;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejercicio1y2 {
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

    public static void mostrarTitulos(Document doc) {
        Node filmoteca, peliculaNode, tituloDirector;
        NodeList pelicula, hijosPelicula;
        filmoteca = doc.getFirstChild();
        pelicula = filmoteca.getChildNodes();
        for (int i = 0; i < pelicula.getLength(); i++) {
            peliculaNode = pelicula.item(i);
            if (peliculaNode.getNodeType() == Node.ELEMENT_NODE) {
                hijosPelicula = peliculaNode.getChildNodes();
                for (int j = 0; j < hijosPelicula.getLength(); j++) {
                    tituloDirector = hijosPelicula.item(j);
                    if (tituloDirector.getNodeType() == Node.ELEMENT_NODE && tituloDirector.getNodeName() == "titulo") {
                        System.out.printf("%s: %s\n", tituloDirector.getNodeName(), tituloDirector.getTextContent());
                    }
                }
            }
        }
    }

    public static void mostrarTodo(Document doc) {
        Node filmoteca, peliculaNode, tituloDirector, director;
        NodeList pelicula, hijosPelicula, directores;
        filmoteca = doc.getFirstChild();
        pelicula = filmoteca.getChildNodes();
        for (int i = 0; i < pelicula.getLength(); i++) {
            peliculaNode = pelicula.item(i);
            if (peliculaNode.getNodeType() == Node.ELEMENT_NODE) {
                hijosPelicula = peliculaNode.getChildNodes();
                for (int j = 0; j < hijosPelicula.getLength(); j++) {
                    tituloDirector = hijosPelicula.item(j);
                    if (tituloDirector.getNodeType() == Node.ELEMENT_NODE) {
                        if (tituloDirector.getNodeName() == "director") {
                            directores = tituloDirector.getChildNodes();
                            System.out.printf("%S: %s %s ",tituloDirector.getNodeName(), directores.item(j).getTextContent(), directores.item(j).getTextContent());
                        } else {
                            System.out.printf("%S: %s ", tituloDirector.getNodeName(), tituloDirector.getTextContent());
                        }
                        System.out.println();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String ruta = "Boletin\\Peliculas.xml";
        Document doc = creaArbol(ruta);

        // mostrarTitulos(doc);

        mostrarTodo(doc);

    }
}
