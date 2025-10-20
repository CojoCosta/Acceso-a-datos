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
        int numero;
        // Ejemplo1 parserSax = new Ejemplo1();
        // parser.parse(entradaXML, parserSax);
        // BoletinSax bolSax = new BoletinSax();
        // parser.parse(entradaXML, bolSax);
//--------------------------- BOLETIN ------------------------------------//
        
        // BoletinSax2 bolSax2 = new BoletinSax2();
        // parser.parse(entradaXML, bolSax2);
        
        // BoletinSax3 bolSax3 = new BoletinSax3();
        // parser.parse(entradaXML, bolSax3);
        
        // BoletinSax4 bolSax4 = new BoletinSax4();
        // parser.parse(entradaXML, bolSax4);

        BoletinSax5 bolSax5 = new BoletinSax5();
        parser.parse(entradaXML, bolSax5);
    }
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        getSax("SAX\\peliculas.xml");
        
    }
}
