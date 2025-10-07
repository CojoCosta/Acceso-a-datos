package Practica;

import java.lang.annotation.ElementType;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

    public static void cachao(Document doc, String tit, String dir, String est, String gen){
        Element nodoPelicula = doc.createElement("Película");
        nodoPelicula.setAttribute("genero", gen);
        nodoPelicula.appendChild(doc.createTextNode("\n"));
        Element nodoTitulo = doc.createElement("Titulo");
        nodoTitulo.appendChild(doc.createTextNode("\n"));
    }


    public static void main(String[] args) {
        String ruta = "Practica\\archivo.xml";
        Document doc = creaArbol(ruta);
        String tit = "Cars";
        String dir = "WaltDisney";
        String est = "2006";
        String gen = "carreras";
        cachao(doc, tit, dir, est, gen);
    }
}
