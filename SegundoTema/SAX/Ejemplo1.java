package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Ejemplo1 extends DefaultHandler{
    String etiqueta = "";
    boolean flag = false;
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String cad = new String(ch, start, length);
        if (flag) {
            System.out.println(cad);
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
        // System.out.println("Valor de etiqueta" + qName);
        etiqueta = qName;
        if (qName.equals("Título")) {
            flag = true;
        }
        if (qName.equals("Película")) {
            for (int i = 0; i < attributes.getLength(); i++) {
                System.out.printf("%s: %s\n",attributes.getLocalName(i), attributes.getValue(i));
            }
        }
    }
    
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("Fin del documento");
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }

}
