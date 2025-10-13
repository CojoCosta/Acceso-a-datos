package Boletin;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

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

    public static void añadirAtributo(Document doc, String tituloPelicula, String atributo) {
        NodeList peliculas = doc.getElementsByTagName("pelicula");
        for (int i = 0; i < peliculas.getLength(); i++) {
            Element pelicula = (Element) peliculas.item(i);
            String titulo = pelicula.getElementsByTagName("titulo").item(0).getTextContent();
            if (titulo.equalsIgnoreCase(tituloPelicula) && !pelicula.hasAttribute(atributo)) {
                pelicula.setAttribute("version", atributo);
                System.out.println("Atributo añadido");
            }
        }
    }

    public static void eliminarAtributo(Document doc, String tituloPelicula, String atributo) {
        NodeList peliculas = doc.getElementsByTagName("pelicula");
        for (int i = 0; i < peliculas.getLength(); i++) {
            Element pelicula = (Element) peliculas.item(i);
            String titulo = pelicula.getElementsByTagName("titulo").item(0).getTextContent();
            if (titulo.equalsIgnoreCase(tituloPelicula) && pelicula.hasAttribute(atributo)) {
                pelicula.removeAttribute(atributo);
                System.out.println(atributo + "eliminado");
            }
        }
    }
    public static void grabarDOM(Document document, String ficheroSalida)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, FileNotFoundException {
        DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
        DOMImplementationLS ls = (DOMImplementationLS) registry.getDOMImplementation("XML 3.0 LS 3.0");
        // Se crea un destino vacio
        LSOutput output = ls.createLSOutput();
        output.setEncoding("UTF-8");
        // Se establece el flujo de salida
        output.setByteStream(new FileOutputStream(ficheroSalida));
        // output.setByteStream(System.out);
        // Permite escribir un documento DOM en XML
        LSSerializer serializer = ls.createLSSerializer();
        // Se establecen las propiedades del serializador
        serializer.setNewLine("\r\n");
        ;
        serializer.getDomConfig().setParameter("format-pretty-print", true);
        // Se escribe el documento ya sea en un fichero o en una cadena de texto
        serializer.write(document, output);
        // String xmlCad=serializer.writeToString(document);
    }

    public static void añadirPelicula(Document doc, String titulo, String nombreAutor, String apellidoAutor,String año, String genero, String idioma){
        Element nodoPelicula = doc.createElement("pelicula");
        nodoPelicula.setAttribute("año",año);
        nodoPelicula.setAttribute("genero", genero);
        nodoPelicula.setAttribute("idioma", idioma);
        nodoPelicula.appendChild(doc.createTextNode("\n"));

        Element nodoTitulo = doc.createElement("titulo");
        nodoTitulo.appendChild(doc.createTextNode(titulo));
        nodoPelicula.appendChild(nodoTitulo);
        nodoTitulo.appendChild(doc.createTextNode("\n"));
        
        Element nodoDirector = doc.createElement("director");
        Node nombre = nodoDirector.appendChild(doc.createElement("nombre"));
        nombre.appendChild(doc.createTextNode(nombreAutor));
        Node apellido = nodoDirector.appendChild(doc.createElement("apellido"));
        apellido.appendChild(doc.createTextNode(apellidoAutor));
        nodoPelicula.appendChild(nodoDirector);
        nodoDirector.appendChild(doc.createTextNode("\n"));
        
        Node raiz = doc.getFirstChild();
        raiz.appendChild(nodoPelicula);
        raiz.appendChild(doc.createTextNode("\n"));
    }
    public static void modificarDirector(Document doc, String nombreNuevo, String nombreViejo, String ape){
        NodeList directores = doc.getElementsByTagName("director");
        for (int i = 0; i < directores.getLength(); i++) {
            Node director = directores.item(i);
            if (director.getFirstChild().getNodeType() != Node.ELEMENT_NODE) {
                Node nombre = director.getFirstChild().getNextSibling();
                NodeList apellidos = doc.getElementsByTagName("apellido");
                for (int j = 0; j < apellidos.getLength(); j++) {
                    if (nombre.getTextContent().equals(nombreViejo) && apellidos.item(j).getTextContent().equals(ape)) {
                        System.out.println(nombre.getTextContent());
                        System.out.println(apellidos.item(j).getTextContent());
                        nombre.setTextContent(nombreNuevo);
                    }
                }
            }
        }
    }

    public static void añadirAlfredo(Document doc, String tituloPelicula){
        NodeList peliculas = doc.getElementsByTagName("pelicula");
        NodeList directores = doc.getElementsByTagName("director");
        for (int i = 0; i < peliculas.getLength(); i++) {
            Node titulo = peliculas.item(i).getFirstChild().getNextSibling();
            if (titulo.getFirstChild().getNodeType() != Node.ELEMENT_NODE && titulo.getTextContent().equals(tituloPelicula)) {
                System.out.println(titulo.getTextContent());
                Element pelicula = (Element) titulo.getParentNode();
                Element nuevoDirector = doc.createElement("director");
                pelicula.appendChild(nuevoDirector);
                pelicula.appendChild(doc.createTextNode("\n"));
                Element nombreDirector = doc.createElement("nombre");
                nombreDirector.appendChild(doc.createTextNode("Alfredo"));

            }
        }
    }
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
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
        // System.out.println("Ejercicio 7");
        // añadirAtributo(doc, "Dune", "Contenido");
        // eliminarAtributo(doc, "Dune", "version");
        // System.out.println("Ejercicio 8");
        // añadirPelicula(doc, "Depredador", "John", "Tiernan", "1987", "acción", "vo");
        // grabarDOM(doc, ruta);
        // System.out.println("Ejercicio 9");
        // modificarDirector(doc, "Lana", "Larry", "Wachowski");
        // grabarDOM(doc, ruta);
        añadirAlfredo(doc, "Dune");
    }
}
