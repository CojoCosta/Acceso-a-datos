import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BoletinRepSax5 extends DefaultHandler {
    boolean flag = false;
    String peliculas = "";
    int contador  = 0;
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        peliculas = new String(ch, start, length);
        if (flag) {
            System.out.println(peliculas);
            flag = false;
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals("peliculas")) {
            
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.equals("pelicula")) {
            if (qName.equals("director")) {
                cont ++;
                flag = false;
            }
            contador = 0;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
