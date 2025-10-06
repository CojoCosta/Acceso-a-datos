package Boletin;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejercicio1y2y3 {
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
        NamedNodeMap atributos;
        filmoteca = doc.getFirstChild();
        pelicula = filmoteca.getChildNodes();
        for (int i = 0; i < pelicula.getLength(); i++) {
            peliculaNode = pelicula.item(i);
            if (peliculaNode.hasAttributes()) {
                atributos = peliculaNode.getAttributes();
                for (int j2 = 0; j2 < atributos.getLength(); j2++) {
                    if (atributos.item(j2).getNodeName() == "genero") {
                        System.out.printf("%S: %s\n", atributos.item(j2).getNodeName(), atributos.item(j2).getTextContent());
                    }
                }
            }
            if (peliculaNode.getNodeType() == Node.ELEMENT_NODE) {
                hijosPelicula = peliculaNode.getChildNodes();
                for (int j = 0; j < hijosPelicula.getLength(); j++) {
                    tituloDirector = hijosPelicula.item(j);
                    if (tituloDirector.getNodeType() == Node.ELEMENT_NODE) {
                        if (tituloDirector.getNodeName() == "director") {
                            directores = tituloDirector.getChildNodes();
                            for (int k = 0; k < directores.getLength(); k++) {
                                director = directores.item(k);
                                if (director.getNodeType() == Node.ELEMENT_NODE) {
                                    System.out.printf("%S: %s ", directores.item(k).getNodeName(), directores.item(k).getTextContent());
                                }
                            }
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

        mostrarTitulos(doc);

        mostrarTodo(doc);

        
    }
}
