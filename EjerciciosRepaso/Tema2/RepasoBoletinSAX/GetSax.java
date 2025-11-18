import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class GetSax {
    public static void getSax(String entradaXML) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        System.out.println("-------------EJ 2 (TITULOS PELICULAS)----------");
        BoletinRepSax2 bolRepSax2 = new BoletinRepSax2();
        // parser.parse(entradaXML, bolRepSax2);
        
        BoletinRepSax3 bolRepSax3 = new BoletinRepSax3();
        // parser.parse(entradaXML, bolRepSax3);
        
        BoletinRepSax14 bolSax4 = new BoletinRepSax14();
        parser.parse(entradaXML, bolSax4);

        BoletinRepSax5 bolRepSax5 = new BoletinRepSax5();
        // parser.parse(entradaXML, bolRepSax5);
    }
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        getSax("EjerciciosRepaso\\Tema2\\RepasoBoletinSAX\\Peliculas.xml");
        
    }
}
