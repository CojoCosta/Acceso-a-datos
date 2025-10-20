package SAX;

import java.nio.channels.Pipe.SourceChannel;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BoletinSax4 extends DefaultHandler {
    String contenido = "";
    boolean flag = false;

    public int numDirectores(int numero) {
        return numero;
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        contenido = new String(ch, start, length);
        if (flag) {
            System.out.print(contenido);
            flag = false;
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("EJERCICIO 16");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        
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
