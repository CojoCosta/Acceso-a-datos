
package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BoletinSax2 extends DefaultHandler {
    String titulo = "";
    boolean flag = false;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        titulo = new String(ch, start, length);
        if (flag) {
            System.out.print(titulo);
            flag = false;
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("Empiezo a leer");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals("filmoteca")) {
            System.out.printf("<%s>\n", qName);
        } else if (qName.equals("pelicula")) {
            System.out.printf("\t<%s", qName);
            for (int i = 0; i < attributes.getLength(); i++) {
                System.out.printf(" %s: %s ", attributes.getLocalName(i), attributes.getValue(i));
            }
            System.out.println(">");
        } else if (qName.equals("titulo")) {
            flag = true;
            System.out.printf("\t\t<%s> ", qName);
        } else if (qName.equals("director")) {
            System.out.printf("\t<%s>\n", qName);
        } else if (qName.equals("nombre")) {
            flag = true;
            System.out.printf("\t\t\t<%s> ", qName);
        } else if (qName.equals("apellido")) {
            flag = true;
            System.out.printf("\t\t\t<%s> ", qName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.equals("filmoteca")) {
            System.out.printf("</%s>\n", qName);
        } else if (qName.equals("pelicula")) {
            System.out.printf("\t</%s>\n", qName);
        } else if (qName.equals("titulo")) {
            flag = true;
            System.out.printf(" </%s>", qName);
        } else if (qName.equals("director")) {
            System.out.printf("\t\t</%s>\n", qName);
        } else if (qName.equals("nombre")) {
            System.out.printf(" </%s>\n", qName);
        } else if (qName.equals("apellido")) {
            System.out.printf(" </%s>\n", qName);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("..............................");
    }

}
