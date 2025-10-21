package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BoletinSaxBien extends DefaultHandler {
    String titulo = "";
    boolean flag = false;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        titulo = new String(ch, start, length);
        System.out.print(titulo);
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("EJERCICIO 14");
        System.out.println("Empiezo a leer");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        System.out.printf("<%s>", qName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
            System.out.printf("</%s>", qName);
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("..............................");
    }

}
