import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BoletinRepSax3 extends DefaultHandler {
    String contenido = "";
    boolean flag = false;
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        contenido = new String(ch, start, length);
        if (flag) {
            System.out.printf("%s, ",contenido);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals( "titulo")) {
            flag = true;
        }
        if (qName.equals("pelicula")) {
            for (int i = 0; i < attributes.getLength(); i++) {
                if (attributes.getLocalName(i).equals("genero")) {
                    System.out.println(attributes.getValue(i));
                }
        }
        }
        if (qName == "nombre" || qName == "apellido") {
            flag = true;
        }
        
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        flag = false;
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
