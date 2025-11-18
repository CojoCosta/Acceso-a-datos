package EjerciciosRepaso.Tema2.RepasoSAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Ejercicio1SAX extends DefaultHandler{
    boolean flag = false;
    String temporada = "";
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        temporada = new String(ch, start, length);
        if (flag){
            System.out.println(temporada);
            flag = false;
        }
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("EJERCICIO 1");
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName == "temporada") {
            System.out.print("Temporada: ");
            flag = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
