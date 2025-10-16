package Practica;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class modificarDOM {
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

    public static void grabarDOM(Document document, String ficheroSalida) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
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
        serializer.getDomConfig().setParameter("format-pretty-print", true);
        // Se escribe el documento ya sea en un fichero o en una cadena de texto
        serializer.write(document, output);
        // String xmlCad=serializer.writeToString(document);
    }

    public static void cachao(Document doc, String tit, String dir, String est, String gen) {
        Element nodoPelicula = doc.createElement("Película");
        nodoPelicula.setAttribute("genero", gen);
        nodoPelicula.appendChild(doc.createTextNode("\n"));

        Element nodoTitulo = doc.createElement("Título");
        Text textTitulo = doc.createTextNode(tit);
        nodoTitulo.appendChild(textTitulo);
        nodoPelicula.appendChild(nodoTitulo);
        nodoPelicula.appendChild(doc.createTextNode("\n"));

        Element nodoDirector = doc.createElement("Director");
        nodoDirector.appendChild(doc.createTextNode(dir));
        nodoPelicula.appendChild(nodoDirector);
        nodoPelicula.appendChild(doc.createTextNode("\n"));

        Element nodoEstreno = doc.createElement("Estreno");
        nodoEstreno.appendChild(doc.createTextNode(est));
        nodoPelicula.appendChild(nodoEstreno);
        nodoPelicula.appendChild(doc.createTextNode("\n"));

        Node nodoPeliculas = doc.getFirstChild();
        nodoPeliculas.appendChild(nodoPelicula);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
        String ruta = "Practica\\archivo.xml";
        Document doc = creaArbol(ruta);
        String tit = "Cars";
        String dir = "WaltDisney";
        String est = "2006";
        String gen = "carreras";
        cachao(doc, tit, dir, est, gen);
        grabarDOM(doc, ruta);
    }
}
