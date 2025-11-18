package EjerciciosRepaso.Tema2.RepasoSAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Ejercicio2SAX extends DefaultHandler{
    int contador = 0;
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName == "evento") {
            contador ++;
        }
    }
    
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }
    
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.printf("Numero de partidos: %d",contador);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }
    
}
