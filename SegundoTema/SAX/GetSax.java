package SAX;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class GetSax {
    public static void getSax(String entradaXML) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        // Ejemplo1 parserSax = new Ejemplo1();
        // parser.parse(entradaXML, parserSax);
        // BoletinSax bolSax = new BoletinSax();
        // parser.parse(entradaXML, bolSax);
        BoletinSax2 bolSax2 = new BoletinSax2();
        parser.parse(entradaXML, bolSax2);
    }
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        getSax("SAX\\peliculas.xml");
        
    }
}
