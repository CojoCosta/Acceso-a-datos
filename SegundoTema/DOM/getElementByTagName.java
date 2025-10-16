package Practica;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class getElementByTagName {

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
        String ruta = "archivo.xml";
        Document doc = creaArbol(ruta);
        NodeList titulos;
        titulos = doc.getElementsByTagName("Título");
        for (int i = 0; i < titulos.getLength(); i++) {
            System.out.printf("%s: %s\n", "Título", titulos.item(i).getTextContent());
        }
    }
}
