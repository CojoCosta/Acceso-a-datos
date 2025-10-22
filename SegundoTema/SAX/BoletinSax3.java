package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BoletinSax3 extends DefaultHandler {
    String contenido = "";
    boolean flag = false;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        contenido = new String(ch, start, length);
        if (flag) {
            System.out.print(contenido + "\n");
            flag = false;
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("EJERCICIO 15");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals("titulo")) {
            flag = true;
            System.out.printf("%s: ", qName);
        } else if (qName.equals("pelicula")) {
            System.out.println("\nPelicula:");
            for (int i = 0; i < attributes.getLength(); i++) {
                if (attributes.getLocalName(i).equals("genero")) {
                    System.out.printf("%s: %s\n", attributes.getLocalName(i), attributes.getValue(i));
                }
            }
        } else if (qName.equals("nombre")) {
            flag = true;
            System.out.printf("%s: ", qName);
        } else if (qName.equals("apellido")) {
            System.out.printf("%s: ", qName);
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
        System.out.println("Adios");
    }

}
