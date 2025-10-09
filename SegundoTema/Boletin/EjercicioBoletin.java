package Boletin;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EjercicioBoletin {
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
                        System.out.printf("%S: %s\n", atributos.item(j2).getNodeName(),
                                atributos.item(j2).getTextContent());
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
                                    System.out.printf("%S: %s ", directores.item(k).getNodeName(),
                                            directores.item(k).getTextContent());
                                }
                            }
                        } else {
                            System.out.printf("%S: %s", tituloDirector.getNodeName(), tituloDirector.getTextContent());
                        }
                        System.out.println();
                    }
                }
            }
        }
    }

    public static void contarDirectores(Document doc, int n) {
        Node filmotecaNode;
        Element peliculaNode;
        NodeList pelicula, directores, titulos;
        filmotecaNode = doc.getFirstChild();
        pelicula = filmotecaNode.getChildNodes();
        for (int i = 0; i < pelicula.getLength(); i++) {
            if (pelicula.item(i).getNodeType() == Node.ELEMENT_NODE) {
                peliculaNode = (Element) pelicula.item(i);
                directores = peliculaNode.getElementsByTagName("director");
                titulos = peliculaNode.getElementsByTagName("titulo");
                if (directores.getLength() > n) {
                    System.out.printf("%S: %s\n", titulos.item(0).getNodeName(), titulos.item(0).getTextContent());
                }
            }
        }
    }

    public static void diferentesGeneros(Document doc) {
        Node filmotecaNode, peliculaNode;
        NodeList pelicula;
        ArrayList<String> generos = new ArrayList<>();
        NamedNodeMap atributos;
        filmotecaNode = doc.getFirstChild();
        pelicula = filmotecaNode.getChildNodes();
        for (int i = 0; i < pelicula.getLength(); i++) {
            if (pelicula.item(i).getNodeType() == Node.ELEMENT_NODE) {
                peliculaNode = (Element) pelicula.item(i);
                if (peliculaNode.hasAttributes()) {
                    atributos = peliculaNode.getAttributes();
                    for (int j = 0; j < atributos.getLength(); j++) {
                        if (atributos.item(j).getNodeName() == "genero") {
                            if (!generos.contains(atributos.item(j).getTextContent())) {
                                generos.add(atributos.item(j).getTextContent());
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < generos.size(); i++) {
            System.out.println(generos.get(i));
        }
        System.out.printf("Nº DE GENEROS DIFRENTES %d", generos.size());
    }

    public static void añadirAtributo(Document doc, String titulo, String atributo) {
        Node filmoteca;
        NodeList pelicula, titulos;
        Element peliculaNode;
        NamedNodeMap atributos;
        filmoteca = doc.getFirstChild();
        pelicula = filmoteca.getChildNodes();
        for (int i = 0; i < pelicula.getLength(); i++) {
            if (pelicula.item(i).getNodeType() == Node.ELEMENT_NODE) {
                peliculaNode = (Element) pelicula.item(i);
                titulos = peliculaNode.getElementsByTagName("titulo");
                if (titulos.item(i).getTextContent() == titulo) {
                    atributos = peliculaNode.getAttributes();
                    for (int j = 0; j < atributos.getLength(); j++) {
                        if (atributos.item(j).getNodeName() != atributo) {
                            peliculaNode.setAttribute(atributo, "150 mins");
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String ruta = "Boletin\\Peliculas.xml";
        Document doc = creaArbol(ruta);
        // System.out.println("Ejercicio 2");
        // mostrarTitulos(doc);

        // System.out.println("Ejercicio 3");
        // mostrarTodo(doc);
        // System.out.println("Ejercicio 5");
        // contarDirectores(doc, 1);
        // System.out.println("Ejercicio 6");
        // diferentesGeneros(doc);
        System.out.println("Ejercicio 7");
        añadirAtributo(doc, "Dune", "duracion");

    }
}
