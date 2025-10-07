package Practica;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class prueba {
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
    public static void recorrerArbol(Document doc, String titulo){
        NodeList titulos = doc.getElementsByTagName("Título");
        Node titu;
        NodeList aux, aux2;
        Element  padre;
        for (int i = 0; i < titulos.getLength(); i++) {
            titu = titulos.item(i);
            if (titu.getTextContent().trim().equals(titulo)) {
                System.out.println(titu.getTextContent());
                padre = (Element) titu.getParentNode();
                aux = padre.getElementsByTagName("Director");
                aux2 = padre.getElementsByTagName("Estreno");
                System.out.println(aux.item(0).getFirstChild().getNodeValue());
                System.out.println(aux2.item(0).getFirstChild().getNodeValue());
            }
        }
    }
    public static void main(String[] args) {
        String ruta = "Practica\\archivo.xml";
        Document doc = creaArbol(ruta);
        recorrerArbol(doc, "Dune");
    }
}
