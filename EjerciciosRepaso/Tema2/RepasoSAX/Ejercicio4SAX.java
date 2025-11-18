package EjerciciosRepaso.Tema2.RepasoSAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Ejercicio4SAX extends DefaultHandler {
    int maxGoles = 0;
    String cantidadGoles;
    String nombreEquipo;
    boolean flag = false;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        cantidadGoles = new String(ch, start, length);
        nombreEquipo = new String(ch, start, length);
        if (flag) {
            if (Integer.parseInt(cantidadGoles) > maxGoles) {
                maxGoles = Integer.parseInt(cantidadGoles);
            }
            flag = false;
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName == "goals_scored") {
            flag = true;
        }
        
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println(maxGoles);
    }

}
