package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BoletinSax4 extends DefaultHandler {
    String contenido = "";
    boolean flag = false;
    int numero = 1;
    int cont = 0;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        contenido = new String(ch, start, length);
        if (flag) {
            System.out.printf("%s tu vieja",contenido);
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
        if (qName.equals("director")) {
            cont++;
            if (cont > numero) {
                flag = true;
                System.out.printf("%s: ", qName);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(qName.equals("pelicula")){
            cont = 0;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
