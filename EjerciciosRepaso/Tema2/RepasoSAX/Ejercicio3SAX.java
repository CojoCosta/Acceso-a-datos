package EjerciciosRepaso.Tema2.RepasoSAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Ejercicio3SAX extends DefaultHandler{
    String contenido = "";
    boolean flag = false;
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        contenido = new String(ch, start, length);
        if(flag){
            System.out.println(contenido);
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
        if(qName == "equipolocal" || qName == "equipovisitante" || qName == "fecha"){
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
    }
}
