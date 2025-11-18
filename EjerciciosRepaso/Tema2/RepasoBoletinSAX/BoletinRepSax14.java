import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BoletinRepSax14 extends DefaultHandler {
    boolean flag = false;
    String cont = "";
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        cont  = new String(ch, start, length);
        if (flag) {
            System.out.print(cont);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        System.out.print("<"+qName+">");
        flag = true;
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        System.out.println("<"+qName+"/>");
        
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        flag = false;
    }
}
