package EjerciciosRepaso.Tema2.RepasoSAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Ejercicio4SAX extends DefaultHandler {
    int maxGoles = 0;
    int cantidadGoles;
    String equipo;
    String equipoGoleador;
    boolean esGol = false;
    boolean esEquipo = false;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        
        if (esGol) {
            cantidadGoles = Integer.parseInt(new String(ch, start, length));
        }
        
        if (esEquipo) {
            equipo = new String(ch, start, length);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName == "name") {
            esEquipo = true;
        }
        if (qName == "goals_scored") {
            esGol = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName == "name") {
            esEquipo = false;
        }else if (qName == "goals_scored"){
            esGol = false;
        }else if (qName == "team") {
            if (cantidadGoles > maxGoles) {
                maxGoles = cantidadGoles;
                equipoGoleador = equipo;
            }
            cantidadGoles = 0;
            equipo = "";
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println(equipoGoleador +": "+ maxGoles);
    }
}
